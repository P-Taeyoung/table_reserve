package com.zerobase.table_reserve.manager.service.impl;

import com.zerobase.table_reserve.manager.domain.entity.Shop;
import com.zerobase.table_reserve.manager.domain.form.ShopDto;
import com.zerobase.table_reserve.manager.domain.form.ShopForm;
import com.zerobase.table_reserve.manager.domain.form.ShopUpdateForm;
import com.zerobase.table_reserve.manager.domain.repository.ShopRepository;
import com.zerobase.table_reserve.manager.service.ManagerResService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerResServiceImpl implements ManagerResService {

    private final ShopRepository shopRepository;

    @Override
    @Transactional
    public ShopDto regShop(Long managerId, ShopForm form) {

        return ShopDto.from(shopRepository.save(Shop.from(managerId, form)));
    }

    @Override
    @Transactional
    public ShopDto updateShop(Long id, ShopUpdateForm form) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        shop.setName(form.getName());
        shop.setLocate(form.getLocate());
        shop.setDescription(form.getDescription());

        return ShopDto.from(shop);
    }

    @Override
    @Transactional
    public void deleteShop(Long id) {
        Shop shop = shopRepository.findById(id).orElseThrow(RuntimeException::new);
        shopRepository.delete(shop);
    }


}
