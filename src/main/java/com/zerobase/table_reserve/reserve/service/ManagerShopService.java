package com.zerobase.table_reserve.reserve.service;

import com.zerobase.table_reserve.reserve.domain.shop.ShopDto;
import com.zerobase.table_reserve.reserve.domain.shop.ShopForm;
import com.zerobase.table_reserve.reserve.domain.shop.ShopUpdateForm;

public interface ManagerShopService {

    /**
     * 매장 등록
     */
    ShopDto regShop(String managerId, ShopForm form);

    /**
     * 매장 수정
     */
    ShopDto updateShop(Long shopId, ShopUpdateForm form);

    /**
     * 매장 삭제
     */
    void deleteShop(Long id);
}
