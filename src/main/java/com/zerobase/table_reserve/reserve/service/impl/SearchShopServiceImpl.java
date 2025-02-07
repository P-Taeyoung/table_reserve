package com.zerobase.table_reserve.reserve.service.impl;

import com.zerobase.table_reserve.exception.CustomException;
import com.zerobase.table_reserve.exception.ErrorCode;
import com.zerobase.table_reserve.reserve.domain.repository.ShopRepository;
import com.zerobase.table_reserve.reserve.domain.shop.SearchShopDto;
import com.zerobase.table_reserve.reserve.domain.shop.ShopDto;
import com.zerobase.table_reserve.reserve.service.SearchShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchShopServiceImpl implements SearchShopService {

    private final ShopRepository shopRepository;

    @Override
    public List<SearchShopDto> searchShop(String name) {
        List<SearchShopDto> searchShopDtos =
                shopRepository.searchByNameContaining(name).map(SearchShopDto::from)
                        .orElse(Collections.emptyList());
        if (searchShopDtos.isEmpty()) {
            //TODO 검색된 매장이 없다고 처리
            throw new CustomException(ErrorCode.NOT_FOUND_SHOP);
        }
        return searchShopDtos;
    }

    @Override
    public ShopDto getDetail(long shopId) {
        return shopRepository.findById(shopId).map(ShopDto::from)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_SHOP));
    }
}
