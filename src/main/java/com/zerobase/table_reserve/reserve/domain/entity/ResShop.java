package com.zerobase.table_reserve.reserve.domain.entity;

import com.zerobase.table_reserve.member.domain.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.envers.AuditOverride;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
//예약내역 엔티티
public class ResShop extends BaseEntity implements ResShopCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//reserve_id

    private Long shopId;
    private String cusId;
    private String managerId;

    private LocalDateTime reserveTime; //예약시간
    private String resStatus; //예약상태

    private Integer numOfGuests;
}
