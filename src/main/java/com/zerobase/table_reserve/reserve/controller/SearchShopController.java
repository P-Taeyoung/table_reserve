package com.zerobase.table_reserve.reserve.controller;

import com.zerobase.table_reserve.reserve.domain.shop.SearchShopDto;
import com.zerobase.table_reserve.reserve.service.SearchShopService;
import com.zerobase.table_reserve.reserve.domain.shop.ShopDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search/shop")
@RequiredArgsConstructor
public class SearchShopController {

    private final SearchShopService searchShopService;

    //가게 검색
    @GetMapping
    public ResponseEntity<List<SearchShopDto>> searchShop(@RequestParam String shopName) {
            List<SearchShopDto> shopDtoList = searchShopService.searchShop(shopName);
            return ResponseEntity.ok(shopDtoList);
    }

    //가게 상세정보 조회
    @GetMapping("/detail")
    public ResponseEntity<ShopDto> detailShop(@RequestParam long shopId) {
        ShopDto shopDto = searchShopService.getDetail(shopId);
        return ResponseEntity.ok(shopDto);
    }
}
