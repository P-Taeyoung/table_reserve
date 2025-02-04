package com.zerobase.table_reserve.manager.domain.entity;

import com.zerobase.table_reserve.manager.domain.form.ShopForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//table_id

    private Long managerId;
    private String name; // 매장명
    private String locate; // 위치
    private String description; // 설명

    public static Shop from(Long id,ShopForm form) {
        return Shop.builder()
                .managerId(id)
                .name(form.getName())
                .locate(form.getLocate())
                .description(form.getDescription())
                .build();
    }

}
