package com.zerobase.table_reserve.reserve.domain.repository;

import com.zerobase.table_reserve.reserve.domain.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<List<Shop>> searchByNameContaining(String name);
}
