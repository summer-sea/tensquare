package com.tensquare.base;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-05-20 17:27
 **/

@SpringBootApplication
@EnableEurekaClient
public class BaseApplication {
    public static void main(String[] args){
        SpringApplication.run(BaseApplication.class);
    }

    //将idworker 放入容器中
    @Bean
    public IdWorker idWorker(){
        return  new IdWorker(1,1);
    }


}
