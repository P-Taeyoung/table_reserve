package com.zerobase.table_reserve.review.service;

import com.zerobase.table_reserve.review.domain.form.ReviewDto;
import com.zerobase.table_reserve.review.domain.form.ReviewForm;
import com.zerobase.table_reserve.review.domain.form.UpdateReviewForm;

public interface ReviewService {
    /**
     * 리뷰 등록
     */
    ReviewDto regReview(Long cusId, ReviewForm form);

    /**
     * 리뷰 수정
     */
    ReviewDto upReview(Long cusId, Long reviewId, UpdateReviewForm form);

    /**
     * 리뷰삭제
     */
    void deleteReview(Long id, Long reviewId);
}
