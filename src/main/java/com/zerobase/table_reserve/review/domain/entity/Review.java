package com.zerobase.table_reserve.review.domain.entity;

import com.zerobase.table_reserve.member.domain.entity.BaseEntity;
import com.zerobase.table_reserve.review.domain.form.ReviewForm;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.AuditOverride;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cusId;
    private Long managerId;
    private Long shopId;
    private Long regId;


    private Rating rating;

    @Lob
    private String contents;
}
