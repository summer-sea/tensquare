package com.tensquare.sms.listenter;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-05-30 17:06
 **/

@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @RabbitHandler
    public void excutSms(Map<String,String> map){
        System.out.println("手机号 "+map.get("mobile"));
        System.out.println("验证码 "+map.get("checkcode"));
    }
}
