package edu.sdp.project.pethospital.config;

import edu.sdp.project.pethospital.service.ImageService;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PHVideoWebMvcConfig implements WebMvcConfigurer {
    public static String videoToStorage = new ApplicationHome(ImageService.class).getSource().getParentFile().getPath() + "/images/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/videos/**")
                // local directory
                .addResourceLocations("file:D:/JavaProject/PetHospital/target/videos/")
                // local dynamic directory
                .addResourceLocations("file:"+videoToStorage);
        // remote directory
        //.addResourceLocations("file:/root/PetHospital/videos/");

//        System.out.println(videoToStorage);
    }
}
