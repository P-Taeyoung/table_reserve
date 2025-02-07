package com.zerobase.table_reserve.reserve.controller;

import com.zerobase.table_reserve.reserve.domain.shop.SearchShopDto;
import com.zerobase.table_reserve.reserve.domain.shop.ShopDto;
import com.zerobase.table_reserve.reserve.service.SearchShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search/shop")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('CUSTOMER')")
public class SearchShopController {

    private final SearchShopService searchShopService;

    //매장 검색
    @GetMapping
    public ResponseEntity<List<SearchShopDto>> searchShop(@RequestParam String shopName) {
            List<SearchShopDto> shopDtoList = searchShopService.searchShop(shopName);
            return ResponseEntity.ok(shopDtoList);
    }

    //매장 상세정보 조회
    @GetMapping("/detail")
    public ResponseEntity<ShopDto> detailShop(@RequestParam long shopId) {
        ShopDto shopDto = searchShopService.getDetail(shopId);
        return ResponseEntity.ok(shopDto);
    }
}
