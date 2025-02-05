package com.zerobase.table_reserve.reserve.service.impl;

import com.zerobase.table_reserve.reserve.domain.shop.SearchShopDto;
import com.zerobase.table_reserve.reserve.service.SearchShopService;
import com.zerobase.table_reserve.reserve.domain.shop.ShopDto;
import com.zerobase.table_reserve.reserve.domain.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
            throw new RuntimeException();
        }
        return searchShopDtos;
    }

    @Override
    public ShopDto getDetail(long shopId) {
        return shopRepository.findById(shopId).map(ShopDto::from)
                .orElseThrow(RuntimeException::new);
    }
}
