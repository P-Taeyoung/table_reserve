package com.zerobase.table_reserve.review.domain.form;

import com.zerobase.table_reserve.review.domain.entity.Rating;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReviewForm {
    private Rating rating;
    private String contents;
}
