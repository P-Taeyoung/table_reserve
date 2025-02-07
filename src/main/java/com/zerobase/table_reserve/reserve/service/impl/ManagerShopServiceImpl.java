package com.zerobase.table_reserve.reserve.service.impl;

import com.zerobase.table_reserve.exception.CustomException;
import com.zerobase.table_reserve.exception.ErrorCode;
import com.zerobase.table_reserve.reserve.domain.entity.Shop;
import com.zerobase.table_reserve.reserve.domain.repository.ShopRepository;
import com.zerobase.table_reserve.reserve.domain.shop.ShopDto;
import com.zerobase.table_reserve.reserve.domain.shop.ShopForm;
import com.zerobase.table_reserve.reserve.domain.shop.ShopUpdateForm;
import com.zerobase.table_reserve.reserve.service.ManagerShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManagerShopServiceImpl implements ManagerShopService {

    private final ShopRepository shopRepository;

    @Override
    @Transactional
    public ShopDto regShop(String managerId, ShopForm form) {

        return ShopDto.from(shopRepository.save(Shop.from(managerId, form)));
    }

    @Override
    @Transactional
    public ShopDto updateShop(Long id, ShopUpdateForm form) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_SHOP));
        shop.setName(form.getName());
        shop.setLocate(form.getLocate());
        shop.setReserveLimit(form.getReserveLimit());
        shop.setDescription(form.getDescription());

        return ShopDto.from(shop);
    }

    @Override
    @Transactional
    public void deleteShop(Long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_SHOP));
        shopRepository.delete(shop);
    }


}
