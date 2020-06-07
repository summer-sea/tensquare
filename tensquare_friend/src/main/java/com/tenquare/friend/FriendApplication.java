package com.tenquare.friend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import util.IdWorker;
import util.JwtUtil;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-06-03 18:30
 **/

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class FriendApplication {
        public static void main(String[] args) {
            SpringApplication.run(FriendApplication.class);
        }

        @Bean
        public IdWorker idWorkker(){
            return new IdWorker(1, 1);
        }

        @Bean
        public JwtUtil jwtUtil(){
            return new util.JwtUtil();
        }




}
