package com.zerobase.table_reserve.review.service.impl;

import com.zerobase.table_reserve.reserve.domain.entity.ResShop;
import com.zerobase.table_reserve.reserve.domain.repository.ResShopRepository;
import com.zerobase.table_reserve.review.domain.entity.Review;
import com.zerobase.table_reserve.review.domain.form.ReviewDto;
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
    public ReviewDto regReview(Long cusId, ReviewForm form) {

        Optional<ResShop> optionalResShop = resShopRepository.findById(form.getRegId());

        if (optionalResShop.isEmpty()) {
            throw new RuntimeException();
        }

        ResShop resShop = optionalResShop.get();

        //고객의 예약내역이 아닌 경우
        if (!Objects.equals(cusId, resShop.getCusId())) {
            throw new RuntimeException();
        }

        //사용완료된 예약내역이 아닌경우
        if (!resShop.getResStatus().equals(ResShop.RESERVE_COMPLETE)) {
            throw new RuntimeException();
        }

        //리뷰작성
        return ReviewDto.from(
                reviewRepository.save(
                        Review.builder()
                                .cusId(cusId)
                                .regId(form.getRegId())
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
    public ReviewDto upReview(Long cusId, Long reviewId, UpdateReviewForm form) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isEmpty()) {
            throw new RuntimeException();
        }

        Review review = optionalReview.get();

        //사용자가 작성한 리뷰가 아닐때
        if (!Objects.equals(cusId, review.getCusId())) {
            throw new RuntimeException();
        }

        //리뷰수정
        review.setRating(form.getRating());
        review.setContents(form.getContents());

        return ReviewDto.from(review);
    }

    @Override
    @Transactional
    public void deleteReview(Long id, Long reviewId) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isEmpty()) {
            throw new RuntimeException();
        }

        Review review = optionalReview.get();

        //리뷰 작성자도 아니고 해당 매장의 점장도 아닌 경우
        if (!Objects.equals(id, review.getCusId())
                && !Objects.equals(id,review.getManagerId())) {
            throw new RuntimeException();
        }

        reviewRepository.deleteById(reviewId);
    }
}
