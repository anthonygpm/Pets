package dev.anthonygpm.pets.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    /**
     * Upload image to Cloudinary
     * @param image file to be sent
     * @return image URL in Cloudinary
     * @throws IOException if happens any error during upload
     */
    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("The archive can't be empty");
        }

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), 
            ObjectUtils.asMap(
                "folder", "pets",
                "resource_type", "auto"
            ));
        
        return uploadResult.get("url").toString();
    }

    /**
     * Delete image from Cloudinary
     * @param publicId public ID of image in Cloudinary
     * @throws IOException if happens any error during deletion
     */
    public void deleteImage(String publicId) throws IOException {
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }

    /**
     * Extract publicId from Cloudinary URL
     * @param imageUrl complete URL of image
     * @return publicId of image
     */
    public String extractPublicId(String imageUrl) {
        if (imageUrl == null || imageUrl.isEmpty()) {
            return null;
        }
        
        // EXAMPLE: https://res.cloudinary.com/{cloud_name}/image/upload/{version}/{folder}/{publicId}.{format}
        String[] parts = imageUrl.split("/");
        if (parts.length > 0) {
            String lastPart = parts[parts.length - 1];

            int dotIndex = lastPart.lastIndexOf('.');
            if (dotIndex > 0) {
                return "pets/" + lastPart.substring(0, dotIndex);
            }
        }
        return null;
    }
}

