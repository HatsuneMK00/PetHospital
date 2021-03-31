package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.config.PHImageWebMvcConfig;
import edu.sdp.project.pethospital.exception.FileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
@Slf4j
public class ImageService {
    private final String uploadPath;


    public ImageService() {
        uploadPath = PHImageWebMvcConfig.imageToStorage;
    }

    public String storeFile(MultipartFile file) throws FileException {
        String fileName = file.getOriginalFilename();

        try {
            if (fileName == null) {
                throw new FileException("image not uploaded successfully");
            }
            if (fileName.contains("..")) {
                throw new FileException("file has invalid filename");
            }
            File dir = new File(uploadPath);
            if (!dir.exists()){
                if (dir.mkdir()) {
                    log.info(String.format("创建文件上传目录成功%s", uploadPath));
                }else {
                    log.warn(String.format("创建文件上传目录失败%s", uploadPath));
                }
            }
            SimpleDateFormat simpleDateFormat;
            simpleDateFormat = new SimpleDateFormat("ddHHssSSS");

            // Requiring distinctive name -> random generator
            Date date = new Date();
            String str = simpleDateFormat.format(date);
            Random random = new Random();
            int img_ran = random.nextInt() * (99999 - 10000 + 1) + 10000;// 获取5位随机数

            String intervalName = img_ran + "" + str;
            fileName = uploadPath + intervalName +fileName;
            log.info("uploadPath: " + uploadPath);
            log.info("filename: " + fileName);

            File dest = new File(fileName);

            file.transferTo(dest);
            return fileName;

        } catch (Exception e) {
            throw new FileException("image upload fail");
        }
    }
}
