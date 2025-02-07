package com.zerobase.table_reserve.review.domain.entity;

import com.zerobase.table_reserve.member.domain.entity.BaseEntity;
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

    private String cusId;
    private String managerId;
    private Long shopId;
    private Long resId;

    @Enumerated(EnumType.STRING) // Enum을 문자열로 저장
    private Rating rating;

    @Lob
    private String contents;
}
