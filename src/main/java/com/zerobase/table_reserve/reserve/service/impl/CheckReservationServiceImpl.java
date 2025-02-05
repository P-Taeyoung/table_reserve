package com.zerobase.table_reserve.reserve.service.impl;

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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckReservationServiceImpl implements CheckReservationService {

    private final ResShopRepository resShopRepository;

    @Override
    @Transactional
    public List<ResShopDto> selectReservation(Long customerId, Long shopId) {
        //예약목록 불러옴
        List<ResShop> resShops = resShopRepository.findByCusIdAndShopId(shopId, customerId);
        if (resShops.isEmpty()) {
            //TODO 예약된 정보가 없습니다. 처리
            throw new RuntimeException();
        }

        //
        return resShops.stream().map(ResShopDto::from).toList();
    }

    @Override
    @Transactional
    public ResShopDto useReserve(Long reserveId) {
        Optional<ResShop> resShop = resShopRepository.findByIdAndResStatus(reserveId);
        if (resShop.isEmpty()) {
            throw new RuntimeException();
        }

        ResShop reserveShop = resShop.get();

        //도착 시간이 예약 시간 10분전 이후라면 예약사용 불가
        if (LocalDateTime.now().withSecond(0).withNano(0)
                .isAfter(reserveShop.getReserveTime().minusMinutes(10))) {
            //TODO
            throw new RuntimeException();
        }
        reserveShop.setResStatus(ResShopCode.RESERVE_COMPLETE);

        return ResShopDto.from(reserveShop);
    }
}
