package com.zerobase.table_reserve.member.controller;


import com.zerobase.table_reserve.member.application.SignUpApplication;
import com.zerobase.table_reserve.member.domain.form.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpApplication signUpApplication;

    @PostMapping("/manager")
    public ResponseEntity<String> managerSignUp(@RequestBody SignUpForm signUpForm) {
        return ResponseEntity.ok(signUpApplication.managerSignUp(signUpForm));
    }

}
