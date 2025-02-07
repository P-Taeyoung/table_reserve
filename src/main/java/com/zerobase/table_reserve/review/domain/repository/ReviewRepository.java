package com.zerobase.table_reserve.review.domain.repository;

import com.zerobase.table_reserve.review.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByResId(Long resId);
}
