package com.tss.UrlShort.UrlShort.service;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.tss.UrlShort.UrlShort.entity.Url;
import com.tss.UrlShort.UrlShort.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class QrCodeServiceImpl implements  QrCodeService {


        private final CloudinaryService cloudinaryService;
        private  final ShortUrlRepository shortUrlRepository;

        @Override
        public String generateQrCode(Url url) {

            try {

                // -------------------------------------------------
                // 1. Create the short URL
                // -------------------------------------------------

                String shortUrl =
                        "http://localhost:8080/api/urls/"
                                + url.getShortCode();


                // -------------------------------------------------
                // 2. Generate QR Code
                // -------------------------------------------------

                BitMatrix matrix =
                        new MultiFormatWriter().encode(
                                shortUrl,
                                BarcodeFormat.QR_CODE,
                                300,
                                300
                        );


                // -------------------------------------------------
                // 3. Convert QR Code to PNG
                // -------------------------------------------------

                ByteArrayOutputStream outputStream =
                        new ByteArrayOutputStream();

                MatrixToImageWriter.writeToStream(
                        matrix,
                        "PNG",
                        outputStream
                );

                byte[] qrImage =
                        outputStream.toByteArray();


                // -------------------------------------------------
                // 4. Generate Cloudinary public ID
                // -------------------------------------------------

                String publicId =
                        "qr_" + url.getId();


                // -------------------------------------------------
                // 5. Upload to Cloudinary
                // -------------------------------------------------

                String qrLink =
                        cloudinaryService.uploadQrCode(
                                qrImage,
                                publicId
                        );


                // -------------------------------------------------
                // 6. Return Cloudinary URL
                // -------------------------------------------------
                url.setQrLink(qrLink);
                shortUrlRepository.save(url);
                return qrLink;


            } catch (Exception e) {

                throw new RuntimeException(
                        "Failed to generate QR code",
                        e
                );
            }
        }

}
