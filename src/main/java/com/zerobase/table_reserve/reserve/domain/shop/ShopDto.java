package com.zerobase.table_reserve.reserve.domain.shop;

import com.zerobase.table_reserve.reserve.domain.entity.Shop;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

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

    private Integer reserveLimit;

    public static ShopDto from(Shop shop) {
        return ShopDto.builder()
                .id(shop.getId())
                .managerId(shop.getManagerId())
                .name(shop.getName())
                .locate(shop.getLocate())
                .description(shop.getDescription())
                .reserveLimit(shop.getReserveLimit())
                .build();
    }


}
