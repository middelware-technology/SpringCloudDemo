package com.example.order;

import com.example.order.feign_client.UserClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class OrderApplication {


    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(OrderApplication.class, args);

        UserClient bean = run.getBean(UserClient.class);

        while (true) {
            try {
                Thread.sleep(1000);
                String userInfo = bean.getUserInfo();
                log.info("userInfo: {}", userInfo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
