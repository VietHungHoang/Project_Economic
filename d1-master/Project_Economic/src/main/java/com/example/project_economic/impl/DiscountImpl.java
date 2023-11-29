package com.example.project_economic.impl;

import com.example.project_economic.entity.DiscountEntity;
import com.example.project_economic.repository.DiscountRepository;
import com.example.project_economic.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class DiscountImpl implements DiscountService {
    @Autowired
    private DiscountRepository discountRepository;
    @Override
    public DiscountEntity findByName(String name){
        DiscountEntity discountEntity = this.discountRepository.findAllByName(name);
        LocalDateTime expiryDate = LocalDateTime.parse(discountEntity.getExpiryDate());
        if(LocalDateTime.now().compareTo(expiryDate) > 0) return discountEntity;
        else return new DiscountEntity();
    }
}
