package com.tss.UrlShort.UrlShort.repository;

import com.tss.UrlShort.UrlShort.entity.BusinessOffer;
import com.tss.UrlShort.UrlShort.enums.OfferType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessOfferRepository  extends JpaRepository<BusinessOffer,Long> {


        List<BusinessOffer> findByActiveTrue();

        List<BusinessOffer> findByTypeAndActiveTrue(OfferType type);

}
