package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.purchase.PurchaseResponseDto;
import com.tss.URL_Shortening.entity.BusinessOffer;
import com.tss.URL_Shortening.entity.Purchase;
import com.tss.URL_Shortening.entity.ShortUrl;
import com.tss.URL_Shortening.entity.User;
import com.tss.URL_Shortening.enums.OfferType;
import com.tss.URL_Shortening.repository.BusinessOfferRepository;
import com.tss.URL_Shortening.repository.PurchaseRepository;
import com.tss.URL_Shortening.repository.ShortUrlRepository;
import com.tss.URL_Shortening.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService{




        private final PurchaseRepository purchaseRepository;
        private final BusinessOfferRepository businessOfferRepository;
        private final UserRepository userRepository;
        private final ShortUrlRepository urlRepository;


        // =========================================================
        // CREATE PURCHASE
        // =========================================================

        @Override
        @Transactional
        public PurchaseResponseDto createPurchase(
                PurchaseRequestDto request) {

            // -----------------------------------------------------
            // 1. Find User
            // -----------------------------------------------------

            User user =
                    userRepository.findById(request.getUserId())
                            .orElseThrow(() ->
                                    new RuntimeException("User not found"));


            // -----------------------------------------------------
            // 2. Find Business Offer
            // -----------------------------------------------------

            BusinessOffer offer =
                    businessOfferRepository
                            .findById(request.getBusinessOfferId())
                            .orElseThrow(() ->
                                    new RuntimeException("Offer not found"));


            // -----------------------------------------------------
            // 3. Check whether offer is active
            // -----------------------------------------------------

            if (!Boolean.TRUE.equals(offer.getActive())) {

                throw new RuntimeException(
                        "Offer is not active");
            }


            // -----------------------------------------------------
            // 4. Find URL if required
            // -----------------------------------------------------

            ShortUrl url = null;

            if (request.getUrlId() != null) {

                url = urlRepository.findById(request.getUrlId())
                        .orElseThrow(() ->
                                new RuntimeException("URL not found"));
            }


            // -----------------------------------------------------
            // 5. Calculate total amount
            // -----------------------------------------------------

            BigDecimal totalAmount =
                    offer.getPrice()
                            .multiply(
                                    BigDecimal.valueOf(
                                            request.getQuantity()));


            // -----------------------------------------------------
            // 6. Generate transaction ID
            // -----------------------------------------------------

            String transactionId =
                    "TXN-" + UUID.randomUUID();


            // =====================================================
            // 7. APPLY PURCHASE BENEFIT
            // =====================================================

            OfferType offerType = offer.getType();


            // -----------------------------------------------------
            // LINK SLOT OFFER
            // -----------------------------------------------------

            if (OfferType.LINK_SLOT.equals(offerType)) {

                int additionalSlots =
                        offer.getValue()
                                * request.getQuantity();

                int currentSlots =
                        user.getLinkSlots() == null
                                ? 0
                                : user.getLinkSlots();

                user.setLinkSlots(
                        currentSlots + additionalSlots
                );

                userRepository.save(user);
            }


            // -----------------------------------------------------
            // VISITOR LIMIT OFFER
            // -----------------------------------------------------

            else if (OfferType.VISITS.equals(offerType)) {

                // URL is required for visitor limit purchase

                if (url == null) {

                    throw new RuntimeException(
                            "URL is required for visitor limit purchase");
                }


                int additionalVisitors =
                        offer.getValue()
                                * request.getQuantity();

                int currentVisitLimit =
                        url.getVisitsLimit() == null
                                ? 0
                                : url.getVisitsLimit();

                url.setVisitsLimit(
                        currentVisitLimit + additionalVisitors
                );

                urlRepository.save(url);
            }


            // -----------------------------------------------------
            // Unknown offer type
            // -----------------------------------------------------

            else {

                throw new RuntimeException(
                        "Invalid business offer type: " + offerType);
            }


            // =====================================================
            // 8. Create Purchase
            // =====================================================

            Purchase purchase = Purchase.builder()
                    .user(user)
                    .businessOffer(offer)
                    .url(url)
                    .quantity(request.getQuantity())
                    .totalAmount(totalAmount)
                    .transactionId(transactionId)
                    .createdAt(LocalDateTime.now())
                    .build();


            // -----------------------------------------------------
            // 9. Save Purchase
            // -----------------------------------------------------

            purchase =
                    purchaseRepository.save(purchase);


            // -----------------------------------------------------
            // 10. Return response
            // -----------------------------------------------------

            return convertToDto(purchase);
        }


        // =========================================================
        // GET PURCHASE BY ID
        // =========================================================

        @Override
        public PurchaseResponseDto getPurchase(Long id) {

            Purchase purchase =
                    purchaseRepository.findById(id)
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Purchase not found"));

            return convertToDto(purchase);
        }


        // =========================================================
        // GET ALL PURCHASES OF USER
        // =========================================================

        @Override
        public List<PurchaseResponseDto> getPurchasesByUser(
                Long userId) {

            User user =
                    userRepository.findById(userId)
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "User not found"));

            return purchaseRepository.findByUser(user)
                    .stream()
                    .map(this::convertToDto)
                    .toList();
        }


        // =========================================================
        // CONVERT ENTITY → DTO
        // =========================================================

        private PurchaseResponseDto convertToDto(
                Purchase purchase) {

            return PurchaseResponseDto.builder()
                    .id(purchase.getId())

                    .userId(
                            purchase.getUser().getUserId())

                    .businessOfferId(
                            purchase.getBusinessOffer().getId())

                    .urlId(
                            purchase.getUrl() != null
                                    ? purchase.getUrl().getId()
                                    : null)

                    .quantity(
                            purchase.getQuantity())

                    .totalAmount(
                            purchase.getTotalAmount())

                    .transactionId(
                            purchase.getTransactionId())

                    .createdAt(
                            purchase.getCreatedAt())

                    .build();
        }



}
