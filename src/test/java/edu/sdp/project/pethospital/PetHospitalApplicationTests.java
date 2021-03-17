package edu.sdp.project.pethospital;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PetHospitalApplicationTests {

    @Autowired
    private StringEncryptor encryptor;

    /**
     * 获取加密后的url，用户名，密码
     * 将加密后的内容拷贝到配置文件中对应的位置，保留ENC()
     */
    @Test
    public void getPassword() {
        String url = encryptor.encrypt("");
        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("123456");
        System.out.println("database url: " + url);
        System.out.println("database name: " + name);
        System.out.println("database password: " + password);
    }

    @Test
    void contextLoads() {
    }

}
