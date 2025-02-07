package com.zerobase.table_reserve.review.service;

import com.zerobase.table_reserve.review.domain.dto.ReviewDto;
import com.zerobase.table_reserve.review.domain.form.ReviewForm;
import com.zerobase.table_reserve.review.domain.form.UpdateReviewForm;

public interface ReviewService {
    /**
     * 리뷰 등록
     */
    ReviewDto regReview(String cusId, ReviewForm form);

    /**
     * 리뷰 수정
     */
    ReviewDto upReview(String cusId, Long reviewId, UpdateReviewForm form);

    /**
     * 리뷰삭제
     */
    void deleteReview(String id, Long reviewId);
}
