package com.example.project_economic.service;

import com.example.project_economic.dto.HistoryCartDto;
import com.example.project_economic.entity.HistoryCard;
import com.example.project_economic.exception.Money;

import java.util.List;

public interface HistoryCardService {
    void addProductToHistoryCard(Long userId) throws Money;

    List<HistoryCartDto> findByUserId(Long userId);
}
