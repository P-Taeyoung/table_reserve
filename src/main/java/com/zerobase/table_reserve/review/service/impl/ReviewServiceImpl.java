package com.zerobase.table_reserve.review.service.impl;

import com.zerobase.table_reserve.exception.CustomException;
import com.zerobase.table_reserve.exception.ErrorCode;
import com.zerobase.table_reserve.reserve.domain.entity.ResShop;
import com.zerobase.table_reserve.reserve.domain.repository.ResShopRepository;
import com.zerobase.table_reserve.review.domain.dto.ReviewDto;
import com.zerobase.table_reserve.review.domain.entity.Review;
import com.zerobase.table_reserve.review.domain.form.ReviewForm;
import com.zerobase.table_reserve.review.domain.form.UpdateReviewForm;
import com.zerobase.table_reserve.review.domain.repository.ReviewRepository;
import com.zerobase.table_reserve.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ResShopRepository resShopRepository;


    @Override
    @Transactional
    public ReviewDto regReview(String cusId, ReviewForm form) {

        Optional<ResShop> optionalResShop = resShopRepository.findById(form.getResId());

        if (optionalResShop.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_RESERVATION);
        }

        ResShop resShop = optionalResShop.get();

        //고객의 예약내역이 아닌 경우
        if (!Objects.equals(cusId, resShop.getCusId())) {
            throw new CustomException(ErrorCode.INVALID_RESERVATION);
        }

        //사용완료된 예약내역이 아닌경우
        if (!resShop.getResStatus().equals(ResShop.RESERVE_COMPLETE)) {
            throw new CustomException(ErrorCode.REVIEW_AFTER_RESERVATION_COMPLETE);
        }

        //이미 해당 예약내역에 대해 작성된 리뷰가 있는경우
        Optional<Review> optionalReview = reviewRepository.findByResId(resShop.getId());
        if (optionalReview.isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_REVIEW_EXISTS);
        }

        //리뷰작성
        return ReviewDto.from(
                reviewRepository.save(
                        Review.builder()
                                .cusId(cusId)
                                .resId(form.getResId())
                                .shopId(resShop.getShopId())
                                .managerId(resShop.getManagerId())
                                .rating(form.getRating())
                                .contents(form.getContents())
                                .build()
                )
        );
    }

    @Override
    @Transactional
    public ReviewDto upReview(String cusId, Long reviewId, UpdateReviewForm form) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_REVIEW);
        }

        Review review = optionalReview.get();

        // 작성자가 아닐때
        if (!Objects.equals(cusId, review.getCusId())) {
            throw new CustomException(ErrorCode.CANNOT_EDIT);
        }

        //리뷰수정
        review.setRating(form.getRating());
        review.setContents(form.getContents());

        return ReviewDto.from(review);
    }

    @Override
    @Transactional
    public void deleteReview(String id, Long reviewId) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_REVIEW);
        }

        Review review = optionalReview.get();

        //리뷰 작성자도 아니고 해당 매장의 점장도 아닌 경우
        if (!Objects.equals(id, review.getCusId())
                && !Objects.equals(id,review.getManagerId())) {
            throw new CustomException(ErrorCode.CANNOT_DELETE);
        }

        reviewRepository.deleteById(reviewId);
    }
}
