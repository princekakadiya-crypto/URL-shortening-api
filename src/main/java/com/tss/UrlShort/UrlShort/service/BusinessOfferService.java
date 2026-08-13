package com.tss.UrlShort.UrlShort.service;

import com.tss.UrlShort.UrlShort.dto.BusinessOfferRequestDto;
import com.tss.UrlShort.UrlShort.dto.BusinessOfferResponseDto;

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
