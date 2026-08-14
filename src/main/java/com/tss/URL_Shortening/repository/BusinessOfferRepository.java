package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.BusinessOffer;
import com.tss.URL_Shortening.enums.OfferType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessOfferRepository extends  JpaRepository<BusinessOffer, Long> {

        List<BusinessOffer> findByActiveTrue();

        List<BusinessOffer> findByTypeAndActiveTrue(OfferType type);


}
