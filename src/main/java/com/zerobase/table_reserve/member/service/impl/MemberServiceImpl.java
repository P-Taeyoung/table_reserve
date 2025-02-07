package com.zerobase.table_reserve.member.service.impl;

import com.zerobase.table_reserve.member.components.MailComponents;
import com.zerobase.table_reserve.member.domain.common.UserType;
import com.zerobase.table_reserve.member.domain.dto.MemberDto;
import com.zerobase.table_reserve.member.domain.entity.Member;
import com.zerobase.table_reserve.member.domain.form.SignInForm;
import com.zerobase.table_reserve.member.domain.form.SignUpForm;
import com.zerobase.table_reserve.member.repository.MemberRepository;
import com.zerobase.table_reserve.member.service.MemberService;
import com.zerobase.table_reserve.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;

    @Override
    @Transactional
    public String signUp(SignUpForm signUpForm) {
        // 이미 가입한 이메일이 있는지 확인
        if (memberRepository.findById(signUpForm.getId()).isPresent()) {
            throw new RuntimeException();
        }

        //이메일 인증키
        String emailAuthKey = UUID.randomUUID().toString();
        signUpForm.setEmailAuthKey(emailAuthKey);

        //password 암호화
        signUpForm.setPassword(BCrypt.hashpw(signUpForm.getPassword(), BCrypt.gensalt()));

        memberRepository.save(Member.from(signUpForm));

        //인증키 이메일로 발송
        String email = signUpForm.getId();
        String subject = "테이블예약 사이트 가입을 축하드립니다.";
        String text = "<p> 테이블예약 사이트 가입을 축하드립니다.</p><p>아래 링크를 클릭하셔서 가입을 완료해주세요</p>" +
                "<div><a target = '_blank' href = 'http://localhost:8080/member/signUp/verify?key=" + emailAuthKey + "'> 가입 완료 </a></div>";
        mailComponents.sendMail(email, subject, text);


        return "테이블 예약 파트너 가입이 완료되었습니다.";
    }

    @Override
    @Transactional
    public String verifyEmail(String key) {

        Optional<Member> managerOptional = memberRepository.findByEmailAuthKey(key);
        if (managerOptional.isEmpty()) {
            // invalid emailAuthKey
            throw new RuntimeException();
        }

        Member member = managerOptional.get();

        if (member.isEmailAuthYn()) {
            // Already have emailAuth
            throw new RuntimeException();
        }

        member.setEmailAuthYn(true);

        return "이메일 인증이 완료되었습니다.";
    }

    @Override
    @Transactional
    public MemberDto signIn(SignInForm signInForm) {
        //로그인이 유효한지 체크
        Optional<Member> optionalManager = memberRepository.findById(signInForm.getUserId());
        //아이디가 있는지
        if (optionalManager.isEmpty()) {
            throw new RuntimeException();
        }
        Member member = optionalManager.get();
        //비밀번호가 일치하는지
        if (!BCrypt.checkpw(signInForm.getPassword(), member.getPassword())) {
            throw new RuntimeException();
        }

        return MemberDto.builder()
                .id(member.getId())
                .isManager(member.isManager())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Member> managerOptional = memberRepository.findById(userId);
        //계정정보가 없음
        if (managerOptional.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다." + userId);
        }

        Member member = managerOptional.get();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (member.isManager()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(UserType.MANAGER.toString()));
        }
        grantedAuthorities.add(new SimpleGrantedAuthority(UserType.CUSTOMER.toString()));

        return new User(member.getId(), member.getPassword(), grantedAuthorities);
    }
}
