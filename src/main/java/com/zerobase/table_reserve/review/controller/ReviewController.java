package com.zerobase.table_reserve.review.controller;

import com.zerobase.table_reserve.review.domain.entity.Review;
import com.zerobase.table_reserve.review.domain.form.ReviewDto;
import com.zerobase.table_reserve.review.domain.form.ReviewForm;
import com.zerobase.table_reserve.review.domain.form.UpdateReviewForm;
import com.zerobase.table_reserve.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰등록
    @PostMapping
    public ResponseEntity<ReviewDto> review(@RequestParam String cusId, @RequestBody ReviewForm form) {
            ReviewDto reviewDto = reviewService.regReview(cusId, form);
            return ResponseEntity.ok(reviewDto);
    }

    //리뷰수정
    @PutMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ReviewDto> updateReview(@RequestParam String cusId,
                                                  @RequestParam Long reviewId,
                                                  @RequestBody UpdateReviewForm form) {
        ReviewDto reviewDto = reviewService.upReview(cusId, reviewId, form);
        return ResponseEntity.ok(reviewDto);
    }

    //리뷰삭제
    @DeleteMapping
    public ResponseEntity<Void> deleteReview(@RequestParam String id,
                                             @RequestParam Long reviewId) {
        reviewService.deleteReview(id, reviewId);
        return ResponseEntity.ok().build();
    }
}
