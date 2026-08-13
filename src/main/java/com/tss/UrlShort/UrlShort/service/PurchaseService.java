package com.tss.UrlShort.UrlShort.service;

import com.tss.UrlShort.UrlShort.dto.PurchaseRequestDto;
import com.tss.UrlShort.UrlShort.dto.PurchaseResponseDto;

import java.util.List;

public interface PurchaseService {


        PurchaseResponseDto createPurchase(
                PurchaseRequestDto request);

        PurchaseResponseDto getPurchase(Long id);

        List<PurchaseResponseDto> getPurchasesByUser(Long userId);

}
