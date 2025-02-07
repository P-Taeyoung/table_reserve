package com.zerobase.table_reserve.member.service;

import com.zerobase.table_reserve.member.domain.dto.MemberDto;
import com.zerobase.table_reserve.member.domain.form.SignInForm;
import com.zerobase.table_reserve.member.domain.form.SignUpForm;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    /**
     * 점장 회원 가입
     */
    String signUp(SignUpForm signUpForm);

    /**
     * 점장 이메일 인증
     */
    String verifyEmail(String key);

    /**
     * 점장 로그인
     * 점장 토큰 반환
     */
    MemberDto signIn(SignInForm signInForm);
}
