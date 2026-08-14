package com.tss.URL_Shortening.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.tss.URL_Shortening.entity.ShortUrl;
import com.tss.URL_Shortening.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;



@Service
@RequiredArgsConstructor
public class QrCodeServiceImpl implements  QrCodeService{




        private final CloudinaryService cloudinaryService;
        private  final ShortUrlRepository shortUrlRepository;

        @Override
        public String generateQrCode(ShortUrl url) {

            try {


                String shortUrl =
                        "http://localhost:8080/api/urls/"
                                + url.getShortCode();


                BitMatrix matrix =
                        new MultiFormatWriter().encode(
                                shortUrl,
                                BarcodeFormat.QR_CODE,
                                300,
                                300
                        );

                ByteArrayOutputStream outputStream =
                        new ByteArrayOutputStream();

                MatrixToImageWriter.writeToStream(
                        matrix,
                        "PNG",
                        outputStream
                );

                byte[] qrImage =
                        outputStream.toByteArray();



                String publicId =
                        "qr_" + url.getId();


                String qrLink =
                        cloudinaryService.uploadQrCode(
                                qrImage,
                                publicId
                        );



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
