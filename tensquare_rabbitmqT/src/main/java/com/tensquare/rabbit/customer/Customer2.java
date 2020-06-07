package com.tensquare.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-05-29 17:33
 **/
@Component
@RabbitListener(queues = "itthema")
public class Customer2 {

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("itthema： "+msg);

    }
}
