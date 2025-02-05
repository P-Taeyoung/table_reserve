package com.zerobase.table_reserve.reserve.domain.shop;

import com.zerobase.table_reserve.reserve.domain.entity.ResShop;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResShopDto {
    private Long id;//reserve_id

    private LocalDateTime reserveTime; //예약시간
    private String resStatus; //예약상태

    private Integer numOfGuests;

    public static ResShopDto from(ResShop resShop) {
        return ResShopDto.builder()
                .id(resShop.getId())
                .reserveTime(resShop.getReserveTime())
                .resStatus(resShop.getResStatus())
                .numOfGuests(resShop.getNumOfGuests())
                .build();
    }
}
