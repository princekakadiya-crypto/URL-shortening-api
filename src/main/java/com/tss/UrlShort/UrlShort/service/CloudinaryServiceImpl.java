package com.tss.UrlShort.UrlShort.service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements  CloudinaryService {


        private final Cloudinary cloudinary;

        @Override
        public String uploadQrCode(byte[] qrImage, String publicId) {

            try {

                Map<?, ?> result = cloudinary.uploader().upload(
                        qrImage,
                        ObjectUtils.asMap(
                                "public_id", publicId,
                                "resource_type", "image",
                                "folder", "url-shortener/qr-codes"
                        )
                );

                return result.get("secure_url").toString();

            } catch (Exception e) {

                throw new RuntimeException(
                        "Failed to upload QR code to Cloudinary",
                        e
                );
            }
        }

}
