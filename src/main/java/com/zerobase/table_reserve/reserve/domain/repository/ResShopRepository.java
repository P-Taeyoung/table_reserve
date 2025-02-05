package com.zerobase.table_reserve.reserve.domain.repository;

import com.zerobase.table_reserve.reserve.domain.entity.ResShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResShopRepository extends JpaRepository<ResShop, Long> {
    @Query("SELECT SUM(r.numOfGuests) FROM ResShop r " +
            "WHERE r.shopId = :shopId AND r.reserveTime = :reserveTime AND r.resStatus = 'BEFORE'")
    Integer sumNumOfGuestsByShopIdAndReserveTime(@Param("shopId") Long shopId, @Param("reserveTime") LocalDateTime reserveTime);

    @Query("SELECT r FROM ResShop r " +
            "WHERE r.shopId = :shopId AND r.cusId = :cusId AND r.resStatus = 'BEFORE'")
    List<ResShop> findByCusIdAndShopId(@Param("shopId") Long shopId, @Param("cusId") Long cusId);

    @Query("SELECT r FROM ResShop r WHERE r.id = :reserveId AND r.resStatus = 'BEFORE'")
    Optional<ResShop> findByIdAndResStatus(@Param("reserveId") Long reserveId);

}

