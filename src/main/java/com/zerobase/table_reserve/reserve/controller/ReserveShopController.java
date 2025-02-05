package com.zerobase.table_reserve.reserve.controller;

import com.zerobase.table_reserve.reserve.domain.shop.ReqResForm;
import com.zerobase.table_reserve.reserve.domain.shop.ReqResResponse;
import com.zerobase.table_reserve.reserve.service.ReserveShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserve/shop")
@RequiredArgsConstructor
public class ReserveShopController {

    private final ReserveShopService reserveShopService;

    @PostMapping
    public ResponseEntity<ReqResResponse> requestReserveShop(@RequestParam Long customerId, @RequestBody ReqResForm form) {

        return ResponseEntity.ok(reserveShopService.reserveShop(customerId, form));
    }


}
