package com.tss.URL_Shortening.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tss.URL_Shortening.dto.user.CloudinaryUploadResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public CloudinaryUploadResponse uploadImage(MultipartFile file) {
        try {
            Map<?, ?> result =
                    cloudinary.uploader().upload(
                            file.getBytes(),
                            ObjectUtils.asMap("folder", "url-shortening")
                    );

            String imageUrl = result.get("secure_url").toString();
            String publicId = result.get("public_id").toString();

            return new CloudinaryUploadResponse(imageUrl, publicId);

        } catch (IOException e) {

            throw new RuntimeException("Image upload failed", e);
        }
    }

    public void deleteImage(String publicId) {

        try {

            cloudinary.uploader().destroy(
                    publicId,
                    ObjectUtils.emptyMap()
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Image deletion failed",
                    e
            );
        }
    }




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