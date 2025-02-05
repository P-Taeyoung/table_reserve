package com.zerobase.table_reserve.reserve.domain.shop;

import com.zerobase.table_reserve.reserve.domain.entity.ResShop;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqResResponse {

    private Long resId;

    private Long shopId;

    private LocalDateTime reserveTime; //예약시간

    private boolean isReserveSuccess;

    public static ReqResResponse success(ResShop resShop) {
        return ReqResResponse.builder()
                .resId(resShop.getId())
                .shopId(resShop.getShopId())
                .reserveTime(resShop.getReserveTime())
                .isReserveSuccess(true)
                .build();
    }

}
