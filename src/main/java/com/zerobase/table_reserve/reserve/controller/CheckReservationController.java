package com.zerobase.table_reserve.reserve.controller;

import com.zerobase.table_reserve.reserve.domain.shop.ResShopDto;
import com.zerobase.table_reserve.reserve.service.CheckReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reserve")
@PreAuthorize("hasAuthority('CUSTOMER')")
public class CheckReservationController {

    private final CheckReservationService checkReservationService;

    //예약목록 조회
    @GetMapping("/check")
    public ResponseEntity<?> selectReservation (@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long shopId) {
        ResShopDto resShopDto= checkReservationService.selectReservation(userDetails.getUsername(), shopId);
        if(resShopDto==null){
            return ResponseEntity.ok("사용가능한 예약내역이 없습니다.");
        }
        return ResponseEntity.ok(resShopDto);
    }

    //예약사용
    @PutMapping("/use")
    public ResponseEntity<ResShopDto> useReservation (@RequestParam Long reserveId) {
        ResShopDto resShopDtos= checkReservationService.useReserve(reserveId);
        return ResponseEntity.ok(resShopDtos);
    }

}
