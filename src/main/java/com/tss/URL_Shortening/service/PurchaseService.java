package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.purchase.PurchaseRequestDto;
import com.tss.URL_Shortening.dto.purchase.PurchaseResponseDto;

import java.util.List;

public interface PurchaseService {


        PurchaseResponseDto createPurchase(
                PurchaseRequestDto request);

        PurchaseResponseDto getPurchase(Long id);

        List<PurchaseResponseDto> getPurchasesByUser(Long userId);


}
