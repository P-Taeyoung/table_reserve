package com.zerobase.table_reserve.reserve.controller;

import com.zerobase.table_reserve.reserve.domain.shop.ReqResForm;
import com.zerobase.table_reserve.reserve.domain.shop.ReqResResponse;
import com.zerobase.table_reserve.reserve.service.ReserveShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve/shop")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('CUSTOMER')")
public class ReserveShopController {

    private final ReserveShopService reserveShopService;

    //매장 예약
    @PostMapping
    public ResponseEntity<ReqResResponse> requestReserveShop(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ReqResForm form) {

        return ResponseEntity.ok(reserveShopService.reserveShop(userDetails.getUsername(), form));
    }


}
