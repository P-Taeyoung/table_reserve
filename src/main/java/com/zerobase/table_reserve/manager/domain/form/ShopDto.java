package com.zerobase.table_reserve.manager.domain.form;

import com.zerobase.table_reserve.manager.domain.entity.Shop;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {

    private Long id;
    private Long managerId;
    private String name;
    private String locate;
    private String description;

    public static ShopDto from(Shop shop) {
        return ShopDto.builder()
                .id(shop.getId())
                .managerId(shop.getManagerId())
                .name(shop.getName())
                .locate(shop.getLocate())
                .description(shop.getDescription())
                .build();
    }
}
