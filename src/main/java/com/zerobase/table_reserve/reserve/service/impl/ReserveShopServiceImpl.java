package com.zerobase.table_reserve.reserve.service.impl;

import com.zerobase.table_reserve.reserve.domain.entity.ResShop;
import com.zerobase.table_reserve.reserve.domain.entity.Shop;
import com.zerobase.table_reserve.reserve.domain.repository.ResShopRepository;
import com.zerobase.table_reserve.reserve.domain.repository.ShopRepository;
import com.zerobase.table_reserve.reserve.domain.shop.ReqResForm;
import com.zerobase.table_reserve.reserve.domain.shop.ReqResResponse;
import com.zerobase.table_reserve.reserve.service.ReserveShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ReserveShopServiceImpl implements ReserveShopService {

    private final ResShopRepository resShopRepository;
    private final ShopRepository shopRepository;

    @Override
    public ReqResResponse reserveShop(Long customerId, ReqResForm form) {

        Shop shop = shopRepository.findById(form.getShopId()).orElseThrow(RuntimeException::new);


        // 예약시간 세팅
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime resTime = LocalDateTime.parse(form.getReserveTime(), formatter);
        LocalDateTime adjustResTime = adjustTime(resTime);

        //TODO 시간입력을 형식에 맞지 않게 입력했을 때 예외처리해야함

        //해당 예약시간은 현재 시간 이후여야 함.
        if (adjustResTime.isBefore(LocalDateTime.now())) {
            throw new RuntimeException();
        }

        int totalGuests = resShopRepository.sumNumOfGuestsByShopIdAndReserveTime(form.getShopId(), adjustResTime) != null
                ? resShopRepository.sumNumOfGuestsByShopIdAndReserveTime(form.getShopId(), adjustResTime) : 0;


        // 해당 예약시간에 예약정원이 차있으면 예약 불가
        if (shop.getReserveLimit() < totalGuests + form.getNumOfGuests()) {
            throw new RuntimeException();
        }

        // 예약처리
        ResShop resShop = resShopRepository.save(
                ResShop.builder()
                        .shopId(form.getShopId())
                        .cusId(customerId)
                        .reserveTime(adjustResTime)
                        .resStatus(ResShop.RESERVE_BEFORE)
                        .numOfGuests(form.getNumOfGuests())
                        .build()
        );

        return ReqResResponse.success(resShop);
    }

    //입력받은 시간에서 가장 가까운 30분 단위로 조정
    private static LocalDateTime adjustTime(LocalDateTime inputTime) {
        int minute = inputTime.getMinute();
        int adjustment = 0;

        if (minute < 15) {
            adjustment = -minute;
        } else if (minute < 45) {
            adjustment = 30 - minute;
        } else {
            adjustment = 60 - minute;
        }

        // 시간 조정
        return inputTime.plusMinutes(adjustment);
    }
}
