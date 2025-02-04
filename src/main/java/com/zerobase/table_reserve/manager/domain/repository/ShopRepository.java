package com.zerobase.table_reserve.manager.domain.repository;

import com.zerobase.table_reserve.manager.domain.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

}
