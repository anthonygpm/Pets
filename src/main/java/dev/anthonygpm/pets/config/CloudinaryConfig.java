package dev.anthonygpm.pets.config;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        String cloudinaryUrl = dotenv.get("CLOUDINARY_URL");

        if (cloudinaryUrl != null && !cloudinaryUrl.isEmpty()) {
            return new Cloudinary(cloudinaryUrl);
        }

        throw new IllegalStateException("CLOUDINARY_URL não encontrada no arquivo .env");
    }
}
