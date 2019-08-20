package com.github.wxiaoqi.security.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigServer
public class ConfigBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ConfigBootstrap.class, args);
    }
}
