package com.tss.UrlShort.UrlShort.controller;


import com.tss.UrlShort.UrlShort.dto.BusinessOfferRequestDto;
import com.tss.UrlShort.UrlShort.dto.BusinessOfferResponseDto;
import com.tss.UrlShort.UrlShort.service.BusinessOfferService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
@RequiredArgsConstructor
public class BusinessOfferController {

        private final BusinessOfferService businessOfferService;

        @PostMapping
        public ResponseEntity<BusinessOfferResponseDto> createOffer(
                @RequestBody BusinessOfferRequestDto request) {

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(businessOfferService.createOffer(request));
        }

        @GetMapping("/{id}")
        public ResponseEntity<BusinessOfferResponseDto> getOffer(
                @PathVariable Long id) {

            return ResponseEntity.ok(
                    businessOfferService.getOffer(id));
        }

        @GetMapping
        public ResponseEntity<List<BusinessOfferResponseDto>> getAllOffers() {

            return ResponseEntity.ok(
                    businessOfferService.getAllOffers());
        }


        @GetMapping("/active")
        public ResponseEntity<List<BusinessOfferResponseDto>> getActiveOffers() {

            return ResponseEntity.ok(
                    businessOfferService.getActiveOffers());
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<BusinessOfferResponseDto> updateOffer(
                @PathVariable Long id,
                @RequestBody BusinessOfferRequestDto request) {

            return ResponseEntity.ok(
                    businessOfferService.updateOffer(id, request));
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> deleteOffer(
                @PathVariable Long id) {

            businessOfferService.deleteOffer(id);

            return ResponseEntity.noContent().build();
        }

}
