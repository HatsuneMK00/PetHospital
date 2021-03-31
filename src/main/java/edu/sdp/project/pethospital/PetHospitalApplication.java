package edu.sdp.project.pethospital;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.sdp.project.pethospital.mapper")
public class PetHospitalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PetHospitalApplication.class, args);
    }
}
