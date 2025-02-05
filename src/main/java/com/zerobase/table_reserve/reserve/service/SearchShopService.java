package com.zerobase.table_reserve.reserve.service;

import com.zerobase.table_reserve.reserve.domain.shop.SearchShopDto;
import com.zerobase.table_reserve.reserve.domain.shop.ShopDto;

import java.util.List;

public interface SearchShopService {

    /**
     * 문자로 매장검색
     * 해당 문자가 포함된 매장리스트를 반환
     */
    List<SearchShopDto> searchShop(String name);

    /**
     * 매장 상세 정보 탐색
     */
    ShopDto getDetail(long shopId);
}
