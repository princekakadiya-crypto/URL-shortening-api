package com.tss.UrlShort.UrlShort.controller;


import com.tss.UrlShort.UrlShort.dto.PurchaseRequestDto;
import com.tss.UrlShort.UrlShort.dto.PurchaseResponseDto;
import com.tss.UrlShort.UrlShort.entity.Url;
import com.tss.UrlShort.UrlShort.service.PurchaseService;
import com.tss.UrlShort.UrlShort.service.QrCodeService;
import com.tss.UrlShort.UrlShort.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final ShortUrlService shortUrlService;
    private  final QrCodeService qrCodeService;

        @PostMapping
        public ResponseEntity<PurchaseResponseDto> createPurchase(
                @RequestBody PurchaseRequestDto request) {

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(purchaseService.createPurchase(request));
        }

        @GetMapping("/{id}")
        public ResponseEntity<PurchaseResponseDto> getPurchase(
                @PathVariable Long id) {

            return ResponseEntity.ok(
                    purchaseService.getPurchase(id));
        }

        @GetMapping("/user/{userId}")
        public ResponseEntity<List<PurchaseResponseDto>> getUserPurchases(
                @PathVariable Long userId) {

            return ResponseEntity.ok(
                    purchaseService.getPurchasesByUser(userId));
        }


    @PostMapping("/qr/{urlId}")
    public ResponseEntity<String> generateQrCode(
            @PathVariable Long urlId) {

        Url url = shortUrlService.getUrlById(urlId);
        String qrLink = qrCodeService.generateQrCode(url);

        return ResponseEntity.ok(qrLink);
    }






}
