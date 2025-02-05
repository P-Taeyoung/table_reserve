package com.zerobase.table_reserve.reserve.domain.shop;

import lombok.*;

import java.time.LocalDateTime;

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
