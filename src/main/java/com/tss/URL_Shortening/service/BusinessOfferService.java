package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.businessoffer.BusinessOfferRequestDto;
import com.tss.URL_Shortening.dto.businessoffer.BusinessOfferResponseDto;

import java.util.List;

public interface BusinessOfferService {


        BusinessOfferResponseDto createOffer(
                BusinessOfferRequestDto request);

        BusinessOfferResponseDto getOffer(Long id);

        List<BusinessOfferResponseDto> getAllOffers();

        List<BusinessOfferResponseDto> getActiveOffers();

        BusinessOfferResponseDto updateOffer(
                Long id,
                BusinessOfferRequestDto request);

        void deleteOffer(Long id);



}
