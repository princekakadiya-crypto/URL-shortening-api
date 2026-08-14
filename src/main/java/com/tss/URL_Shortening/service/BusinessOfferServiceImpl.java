package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.entity.BusinessOffer;
import com.tss.URL_Shortening.repository.BusinessOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessOfferServiceImpl implements  BusinessOfferService {



        private final BusinessOfferRepository businessOfferRepository;

        @Override
        public BusinessOfferResponseDto createOffer(
                BusinessOfferRequestDto request) {

            BusinessOffer offer = BusinessOffer.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .type(request.getType())
                    .value(request.getValue())
                    .price(request.getPrice())
                    .active(request.getActive())
                    .build();

            return convertToDto(
                    businessOfferRepository.save(offer));
        }

        @Override
        public BusinessOfferResponseDto getOffer(Long id) {

            BusinessOffer offer =
                    businessOfferRepository.findById(id)
                            .orElseThrow(() ->
                                    new RuntimeException("Offer not found"));

            return convertToDto(offer);
        }

        @Override
        public List<BusinessOfferResponseDto> getAllOffers() {

            return businessOfferRepository.findAll()
                    .stream()
                    .map(this::convertToDto)
                    .toList();
        }

        @Override
        public List<BusinessOfferResponseDto> getActiveOffers() {

            return businessOfferRepository.findByActiveTrue()
                    .stream()
                    .map(this::convertToDto)
                    .toList();
        }

        @Override
        public BusinessOfferResponseDto updateOffer(
                Long id,
                BusinessOfferRequestDto request) {

            BusinessOffer offer =
                    businessOfferRepository.findById(id)
                            .orElseThrow(() ->
                                    new RuntimeException("Offer not found"));

            offer.setName(request.getName());
            offer.setDescription(request.getDescription());
            offer.setType(request.getType());
            offer.setValue(request.getValue());
            offer.setPrice(request.getPrice());
            offer.setActive(request.getActive());

            return convertToDto(
                    businessOfferRepository.save(offer));
        }

        @Override
        public void deleteOffer(Long id) {

            if (!businessOfferRepository.existsById(id)) {
                throw new RuntimeException("Offer not found");
            }

            businessOfferRepository.deleteById(id);
        }

        private BusinessOfferResponseDto convertToDto(
                BusinessOffer offer) {

            return BusinessOfferResponseDto.builder()
                    .id(offer.getId())
                    .name(offer.getName())
                    .description(offer.getDescription())
                    .type(offer.getType())
                    .value(offer.getValue())
                    .price(offer.getPrice())
                    .active(offer.getActive())
                    .build();
        }



}
