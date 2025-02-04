package com.zerobase.table_reserve.manager.service;

import com.zerobase.table_reserve.manager.domain.entity.Shop;
import com.zerobase.table_reserve.manager.domain.form.ShopDto;
import com.zerobase.table_reserve.manager.domain.form.ShopForm;
import com.zerobase.table_reserve.manager.domain.form.ShopUpdateForm;

public interface ManagerResService {

    /**
     * 매장 등록
     */
    ShopDto regShop(Long managerId, ShopForm form);

    /**
     * 매장 수정
     */
    ShopDto updateShop(Long shopId, ShopUpdateForm form);

    /**
     * 매장 삭제
     */
    void deleteShop(Long id);
}
