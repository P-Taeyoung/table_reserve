package com.zerobase.table_reserve.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    @PostMapping
    public ResponseEntity<?> regReview(@RequestParam Long customerId, @RequestParam Long reserveId) {


    }
}
