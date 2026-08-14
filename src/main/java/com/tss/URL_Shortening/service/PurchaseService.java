package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.purchase.CreatePurchaseRequestDto;
import com.tss.URL_Shortening.dto.purchase.PurchaseResponseDto;

import java.util.List;

public interface PurchaseService {

    PurchaseResponseDto createPurchase(
            CreatePurchaseRequestDto request);

    PurchaseResponseDto getPurchase(Long id);

    List<PurchaseResponseDto> getPurchasesByUser(Long userId);


}
