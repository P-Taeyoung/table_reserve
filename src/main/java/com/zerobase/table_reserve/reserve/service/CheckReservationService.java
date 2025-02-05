package com.zerobase.table_reserve.reserve.service;

import com.zerobase.table_reserve.reserve.domain.shop.ResShopDto;

import java.util.List;

public interface CheckReservationService {
    //사용자가 해당 가게에 도착하면 자신의 예약내역 조회
    List<ResShopDto> selectReservation(Long customerId, Long shopId);

    //조회 후 해당 예약 사용
    ResShopDto useReserve(Long reserveId);
}
