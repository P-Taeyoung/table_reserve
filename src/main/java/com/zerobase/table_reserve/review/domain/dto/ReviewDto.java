package com.zerobase.table_reserve.review.domain.dto;

import com.zerobase.table_reserve.review.domain.entity.Rating;
import com.zerobase.table_reserve.review.domain.entity.Review;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Long reviewId;

    private String cusId;
    private String managerId;
    private Long shopId;


    private Rating rating;
    private String contents;

    public static ReviewDto from(Review review) {
        return ReviewDto.builder()
                .reviewId(review.getId())
                .cusId(review.getCusId())
                .shopId(review.getShopId())
                .managerId(review.getManagerId())
                .rating(review.getRating())
                .contents(review.getContents())
                .build();
    }
}
