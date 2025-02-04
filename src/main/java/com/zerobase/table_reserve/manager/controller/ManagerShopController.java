package com.zerobase.table_reserve.manager.controller;

import com.zerobase.table_reserve.manager.domain.form.ShopForm;
import com.zerobase.table_reserve.manager.domain.form.ShopUpdateForm;
import com.zerobase.table_reserve.manager.service.ManagerResService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager/shop")
@RequiredArgsConstructor
public class ManagerShopController {
    private final ManagerResService managerResService;

    @PostMapping
    public ResponseEntity<?> regShop(@RequestParam Long managerId
            ,@RequestBody ShopForm form) {

        return ResponseEntity.ok(managerResService.regShop(managerId, form));
    }

    @PutMapping
    public ResponseEntity<?> updateShop(@RequestParam Long shopId
            ,@RequestBody ShopUpdateForm form) {

        return ResponseEntity.ok(managerResService.updateShop(shopId, form));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteShop(@RequestParam Long shopId) {
        managerResService.deleteShop(shopId);
        return ResponseEntity.ok().build();
    }




}
