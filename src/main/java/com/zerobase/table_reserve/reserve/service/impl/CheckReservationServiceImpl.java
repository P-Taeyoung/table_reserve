package com.zerobase.table_reserve.reserve.service.impl;

import com.zerobase.table_reserve.exception.CustomException;
import com.zerobase.table_reserve.exception.ErrorCode;
import com.zerobase.table_reserve.reserve.domain.entity.ResShop;
import com.zerobase.table_reserve.reserve.domain.entity.ResShopCode;
import com.zerobase.table_reserve.reserve.domain.repository.ResShopRepository;
import com.zerobase.table_reserve.reserve.domain.shop.ResShopDto;
import com.zerobase.table_reserve.reserve.service.CheckReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckReservationServiceImpl implements CheckReservationService {

    private final ResShopRepository resShopRepository;

    @Override
    @Transactional
    public ResShopDto selectReservation(String customerId, Long shopId) {
        //예약목록 불러옴
        List<ResShop> resShops = resShopRepository.findByCusIdAndShopId(shopId, customerId);
        if (resShops.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_RESERVATION);
        }

        //현재 시간이 예약시간 10분 전이 지난 예약내역은 예약취소상태로 바꿈
        //예약목록 중 사용가능한 예약 내역중 제일 예약 시간이 빠른 것 하나를 반환
        for (ResShop resShop : resShops) {
            if (LocalDateTime.now().withSecond(0).withNano(0)
                    .isAfter(resShop.getReserveTime().minusMinutes(10))) {
                resShop.setResStatus(ResShopCode.RESERVE_CANCELED);
            } else {
                return ResShopDto.from(resShop);
            }
        }

        return null;
    }

    @Override
    @Transactional
    public ResShopDto useReserve(Long reserveId) {
        Optional<ResShop> resShop = resShopRepository.findByIdAndResStatus(reserveId);
        if (resShop.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_RESERVATION);
        }

        ResShop reserveShop = resShop.get();

        reserveShop.setResStatus(ResShopCode.RESERVE_COMPLETE);

        return ResShopDto.from(reserveShop);
    }
}
