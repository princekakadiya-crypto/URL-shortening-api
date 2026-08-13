package com.tss.UrlShort.UrlShort.service;

import com.tss.UrlShort.UrlShort.entity.Url;

public interface QrCodeService {



        String generateQrCode(Url url);

}
