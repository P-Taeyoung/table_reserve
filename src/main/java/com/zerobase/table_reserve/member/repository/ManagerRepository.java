package com.zerobase.table_reserve.member.repository;

import com.zerobase.table_reserve.member.domain.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, String> {
}
