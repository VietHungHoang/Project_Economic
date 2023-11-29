package com.example.project_economic.repository;

import com.example.project_economic.entity.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<DiscountEntity,Long> {
    DiscountEntity findAllByName(String name);
}
