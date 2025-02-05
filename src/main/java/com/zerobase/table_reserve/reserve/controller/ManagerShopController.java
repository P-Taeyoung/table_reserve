package com.zerobase.table_reserve.reserve.controller;

import com.zerobase.table_reserve.reserve.domain.shop.ShopForm;
import com.zerobase.table_reserve.reserve.domain.shop.ShopUpdateForm;
import com.zerobase.table_reserve.reserve.service.ManagerShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager/shop/register")
@RequiredArgsConstructor
public class ManagerShopController {
    private final ManagerShopService managerShopService;

    @PostMapping
    public ResponseEntity<?> regShop(@RequestParam Long managerId
            ,@RequestBody ShopForm form) {

        return ResponseEntity.ok(managerShopService.regShop(managerId, form));
    }

    @PutMapping
    public ResponseEntity<?> updateShop(@RequestParam Long shopId
            ,@RequestBody ShopUpdateForm form) {

        return ResponseEntity.ok(managerShopService.updateShop(shopId, form));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteShop(@RequestParam Long shopId) {
        managerShopService.deleteShop(shopId);
        return ResponseEntity.ok().build();
    }




}
