package com.zerobase.table_reserve.reserve.domain.shop;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqResForm {

    private Long shopId;

    private String reserveTime; //예약시간

    private Integer numOfGuests;

}
