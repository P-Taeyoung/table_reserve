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
public class SearchShopDto {
    private Long shopId;
    private String shopName;
    private String locate;

    public static List<SearchShopDto> from(List<Shop> shops) {
        return shops.stream().map(shop ->
                SearchShopDto.builder()
                        .shopId(shop.getId())
                        .shopName(shop.getName())
                        .locate(shop.getLocate())
                        .build())
                .collect(Collectors.toList());
    }
}
