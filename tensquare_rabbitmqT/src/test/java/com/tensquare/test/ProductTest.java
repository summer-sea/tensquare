package com.tensquare.test;

import com.tensquare.rabbit.RabbitApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-05-29 14:56
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitApplication.class)
public class ProductTest {



    @Autowired
    private RabbitTemplate rabbitTemplate;

    //直接发送不通过交换器
    @Test
    public void SendMsg(){
        rabbitTemplate.convertAndSend("itcast","我要红包");
    }

    //分裂模式
    @Test
    public void sendnsg2(){
        rabbitTemplate.
                convertAndSend("chuanzhi","good.abc.log","分裂模式测试");
    }

    //主题模式
    @Test
    public void sendnsg3(){
        rabbitTemplate.
                convertAndSend("topic84","good.abc.log","主题模式测试");
    }
}
