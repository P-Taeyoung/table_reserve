package com.zerobase.table_reserve.reserve.controller;

import com.zerobase.table_reserve.reserve.domain.shop.ShopForm;
import com.zerobase.table_reserve.reserve.domain.shop.ShopUpdateForm;
import com.zerobase.table_reserve.reserve.service.ManagerShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop/register")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('MANAGER')")
public class ManagerShopController {
    private final ManagerShopService managerShopService;

    //매장등록
    @PostMapping
    public ResponseEntity<?> regShop(@AuthenticationPrincipal UserDetails userDetails
            ,@RequestBody ShopForm form) {

        return ResponseEntity.ok(managerShopService.regShop(userDetails.getUsername(), form));
    }

    //등록 매장정보 수정
    @PutMapping
    public ResponseEntity<?> updateShop(@RequestParam Long shopId
            ,@RequestBody ShopUpdateForm form) {

        return ResponseEntity.ok(managerShopService.updateShop(shopId, form));
    }

    //등록 매장 제거
    @DeleteMapping
    public ResponseEntity<String> deleteShop(@RequestParam Long shopId) {
        managerShopService.deleteShop(shopId);
        return ResponseEntity.ok("매장이 정상적으로 삭제되었습니다.");
    }




}
