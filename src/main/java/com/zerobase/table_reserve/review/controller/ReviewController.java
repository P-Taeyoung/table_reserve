package com.zerobase.table_reserve.review.controller;

import com.zerobase.table_reserve.review.domain.entity.Review;
import com.zerobase.table_reserve.review.domain.form.ReviewDto;
import com.zerobase.table_reserve.review.domain.form.ReviewForm;
import com.zerobase.table_reserve.review.domain.form.UpdateReviewForm;
import com.zerobase.table_reserve.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDto> review(@RequestParam Long cusId, @RequestBody ReviewForm form) {
            ReviewDto reviewDto = reviewService.regReview(cusId, form);
            return ResponseEntity.ok(reviewDto);
    }

    @PutMapping
    public ResponseEntity<ReviewDto> updateReview(@RequestParam Long cusId,
                                                  @RequestParam Long reviewId,
                                                  @RequestBody UpdateReviewForm form) {
        ReviewDto reviewDto = reviewService.upReview(cusId, reviewId, form);
        return ResponseEntity.ok(reviewDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteReview(@RequestParam Long id,
                                             @RequestParam Long reviewId) {
        reviewService.deleteReview(id, reviewId);
        return ResponseEntity.ok().build();
    }
}
