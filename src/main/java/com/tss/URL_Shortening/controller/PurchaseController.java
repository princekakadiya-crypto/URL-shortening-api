package com.tss.URL_Shortening.controller;

import com.tss.URL_Shortening.dto.purchase.CreatePurchaseRequestDto;
import com.tss.URL_Shortening.dto.purchase.PurchaseResponseDto;
import com.tss.URL_Shortening.entity.ShortUrl;
import com.tss.URL_Shortening.entity.User;
import com.tss.URL_Shortening.service.PurchaseService;
import com.tss.URL_Shortening.service.QrCodeService;
import com.tss.URL_Shortening.service.ShortUrlService;
import com.tss.URL_Shortening.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseController {


        private final PurchaseService purchaseService;
        private final ShortUrlService shortUrlService;
        private  final QrCodeService qrCodeService;
        private  final UserService userService;

        @PostMapping
        public ResponseEntity<PurchaseResponseDto> createPurchase(
                @RequestBody CreatePurchaseRequestDto request) {

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

        @GetMapping("/user/My-Purchases")
        public ResponseEntity<List<PurchaseResponseDto>> getUserPurchases(
                                             Authentication authentication) {
            String username = authentication.getName();
            Optional<User> user = userService.findByName(username);
            Long userId = user.get().getUserId();




            return ResponseEntity.ok(
                    purchaseService.getPurchasesByUser(userId));
        }


        @PostMapping("/qr/{urlId}")
        public ResponseEntity<String> generateQrCode(
                @PathVariable Long urlId) {

            ShortUrl url = shortUrlService.getUrlById(urlId);
            String qrLink = qrCodeService.generateQrCode(url);

            return ResponseEntity.ok(qrLink);
        }





}
