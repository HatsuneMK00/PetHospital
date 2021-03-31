package edu.sdp.project.pethospital.config;

import edu.sdp.project.pethospital.service.ImageService;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PHImageWebMvcConfig implements WebMvcConfigurer {
    public static String imageToStorage = new ApplicationHome(ImageService.class).getSource().getParentFile().getPath() + "/images/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                // local directory
                .addResourceLocations("file:D:/JavaProject/PetHospital/target/images/")
                // local dynamic directory
                .addResourceLocations("file:"+imageToStorage);
        // remote directory
        //.addResourceLocations("file:/root/PetHospital/images/");

//        System.out.println(imageToStorage);
    }
}
