package com.example.order.feign_client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "user-server",path = "/user"
        ,fallbackFactory = UserFallbackFactory.class
)
public interface UserClient {

    @GetMapping("/info")
    String getUserInfo();
}

@Slf4j
@Component
class UserFallbackFactory implements FallbackFactory<UserFallbackWithFactory>{

    @Override
    public UserFallbackWithFactory create(Throwable cause) {
        log.error("获取用户信息失败：{}",cause.getMessage());
        return new UserFallbackWithFactory();
    }
}

@Slf4j
class UserFallbackWithFactory implements UserClient{

    @Override
    public String getUserInfo() {
        throw new NoFallbackAvailableException("Boom!", new RuntimeException());
    }
}