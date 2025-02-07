package com.zerobase.table_reserve.member.controller;

import com.zerobase.table_reserve.member.domain.common.UserType;
import com.zerobase.table_reserve.member.domain.dto.MemberDto;
import com.zerobase.table_reserve.member.domain.form.SignInForm;
import com.zerobase.table_reserve.member.service.MemberService;
import com.zerobase.table_reserve.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member/signIn")
@RequiredArgsConstructor
public class SignInController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    //로그인
    @PostMapping
    public String signIn(@RequestBody SignInForm signInForm) {
        //로그인 유효성 검사
        MemberDto memberDto = memberService.signIn(signInForm);

        // 토큰 발행
        if (memberDto.isManager()) {
            return tokenProvider.createToken(signInForm.getUserId(), UserType.MANAGER);
        }

        return tokenProvider.createToken(signInForm.getUserId(), UserType.CUSTOMER);
    }
}
