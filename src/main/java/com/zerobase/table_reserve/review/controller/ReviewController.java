package com.zerobase.table_reserve.review.controller;

import com.zerobase.table_reserve.review.domain.dto.ReviewDto;
import com.zerobase.table_reserve.review.domain.form.ReviewForm;
import com.zerobase.table_reserve.review.domain.form.UpdateReviewForm;
import com.zerobase.table_reserve.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰등록
    @PostMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<ReviewDto> review(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ReviewForm form) {
            ReviewDto reviewDto = reviewService.regReview(userDetails.getUsername(), form);
            return ResponseEntity.ok(reviewDto);
    }

    //리뷰수정
    @PutMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<ReviewDto> updateReview(@AuthenticationPrincipal UserDetails userDetails,
                                                  @RequestParam Long reviewId,
                                                  @RequestBody UpdateReviewForm form) {
        ReviewDto reviewDto = reviewService.upReview(userDetails.getUsername(), reviewId, form);
        return ResponseEntity.ok(reviewDto);
    }

    //리뷰삭제
    @DeleteMapping
    @PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('MANAGER')")
    public ResponseEntity<String> deleteReview(@AuthenticationPrincipal UserDetails userDetails,
                                             @RequestParam Long reviewId) {
        reviewService.deleteReview(userDetails.getUsername(), reviewId);
        return ResponseEntity.ok("리뷰가 삭제되었습니다.");
    }
}
