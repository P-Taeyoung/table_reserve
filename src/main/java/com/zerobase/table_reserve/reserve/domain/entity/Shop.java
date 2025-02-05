package com.zerobase.table_reserve.reserve.domain.entity;

import com.zerobase.table_reserve.reserve.domain.shop.ShopForm;
import com.zerobase.table_reserve.member.domain.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.envers.AuditOverride;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Shop extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//table_id

    private Long managerId;
    private String name; // 매장명
    private String locate; // 위치
    private String description; // 설명

    private Integer reserveLimit; //예약 타임당 예약인원 정원

    public static Shop from(Long id,ShopForm form) {
        return Shop.builder()
                .managerId(id)
                .name(form.getName())
                .locate(form.getLocate())
                .description(form.getDescription())
                .reserveLimit(form.getReserveLimit())
                .build();
    }

}
