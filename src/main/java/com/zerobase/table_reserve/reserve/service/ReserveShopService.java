package com.zerobase.table_reserve.reserve.service;

import com.zerobase.table_reserve.reserve.domain.shop.ReqResForm;
import com.zerobase.table_reserve.reserve.domain.shop.ReqResResponse;

public interface ReserveShopService {
    /**
     * 매장 예약
     */
    ReqResResponse reserveShop(String customerId, ReqResForm form);
}
