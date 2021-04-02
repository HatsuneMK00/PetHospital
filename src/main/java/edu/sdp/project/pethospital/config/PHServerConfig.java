package edu.sdp.project.pethospital.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@Slf4j
public class PHServerConfig implements ApplicationListener<WebServerInitializedEvent> {
    public static String server;
    public static String port;

    /*
     * 使用构造函数的自动注入
     * 可以自己控制自动注入和构造函数的执行顺序
     * 让自动注入先于构造函数中的调用
     * */
//    @Autowired
    public PHServerConfig() {
        try {
            server = InetAddress.getLocalHost().getHostAddress();
            if (server.startsWith("192.168"))
                server = "localhost";
            log.info(String.format("--------------------------------------server ip address without ex: %s", server));
        } catch (UnknownHostException e) {
            log.error(e.getMessage(),e);
            server = "localhost";
            log.info(String.format("--------------------------------------server ip address with ex: %s", server));
        }
//        server="47.101.217.16";
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        port = String.valueOf(webServerInitializedEvent.getWebServer().getPort());
        log.info(String.format("------------------------------------------port: %s", PHServerConfig.port));
        log.info(String.format("--------------------------------------server ip address: %s", server));
    }
}
