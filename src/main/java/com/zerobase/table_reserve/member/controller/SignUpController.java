package com.zerobase.table_reserve.member.controller;


import com.zerobase.table_reserve.member.domain.form.SignUpForm;
import com.zerobase.table_reserve.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member/signUp")
@RequiredArgsConstructor
public class SignUpController {

    public final MemberService memberService;



    @PostMapping
    public ResponseEntity<String> signUp(@RequestBody SignUpForm signUpForm) {

        return ResponseEntity.ok(memberService.signUp(signUpForm));
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String key) {
        return ResponseEntity.ok(memberService.verifyEmail(key));
    }



}
