package com.zerobase.table_reserve.reserve.controller;

import com.zerobase.table_reserve.reserve.domain.shop.ResShopDto;
import com.zerobase.table_reserve.reserve.service.CheckReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reserve")
public class CheckReservationController {

    private final CheckReservationService checkReservationService;

    @GetMapping("/select")
    public ResponseEntity<List<ResShopDto>> selectReservation (@RequestParam Long customerId, @RequestParam Long shopId) {
        List<ResShopDto> resShopDtos= checkReservationService.selectReservation(customerId, shopId);
        return ResponseEntity.ok(resShopDtos);
    }

    @PutMapping("/use")
    public ResponseEntity<ResShopDto> useReservation (@RequestParam Long reserveId) {
        ResShopDto resShopDtos= checkReservationService.useReserve(reserveId);
        return ResponseEntity.ok(resShopDtos);
    }

}
