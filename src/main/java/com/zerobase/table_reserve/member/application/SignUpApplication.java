package com.zerobase.table_reserve.member.application;

import com.zerobase.table_reserve.member.domain.form.SignUpForm;
import com.zerobase.table_reserve.member.service.SignUpManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpApplication {

    private final SignUpManagerService signUpManagerService;

    public String managerSignUp(SignUpForm signUpForm) {
        if (signUpManagerService.isEmailExist(signUpForm.getId())) {
            throw new RuntimeException();
        } else {
            signUpManagerService.signUp(signUpForm);

            return "회원가입되었습니다.";
        }
    }



}
