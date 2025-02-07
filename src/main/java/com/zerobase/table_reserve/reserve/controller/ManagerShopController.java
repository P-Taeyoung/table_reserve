package com.zerobase.table_reserve.reserve.controller;

import com.zerobase.table_reserve.reserve.domain.shop.ShopForm;
import com.zerobase.table_reserve.reserve.domain.shop.ShopUpdateForm;
import com.zerobase.table_reserve.reserve.service.ManagerShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;

@RestController
@RequestMapping("/shop/register")
@RequiredArgsConstructor
@PreAuthorize("hasRole('MANAGER')")
public class ManagerShopController {
    private final ManagerShopService managerShopService;

    //매장등록
    @PostMapping
    public ResponseEntity<?> regShop(@AuthenticationPrincipal UserPrincipal userPrincipal
            ,@RequestBody ShopForm form) {

        return ResponseEntity.ok(managerShopService.regShop(userPrincipal.getName(), form));
    }

    //등록 매장정보 수정
    @PutMapping
    public ResponseEntity<?> updateShop(@RequestParam Long shopId
            ,@RequestBody ShopUpdateForm form) {

        return ResponseEntity.ok(managerShopService.updateShop(shopId, form));
    }

    //등록 매장 제거
    @DeleteMapping
    public ResponseEntity<Void> deleteShop(@RequestParam Long shopId) {
        managerShopService.deleteShop(shopId);
        return ResponseEntity.ok().build();
    }




}
