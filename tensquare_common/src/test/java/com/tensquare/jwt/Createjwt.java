package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-06-01 16:08
 **/

public class Createjwt {

    @Test
    public  void jwt() {
        //生成jwt
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("666")
                .setSubject("小马") //用户名
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+ 60000)) //设置过期时间
                .signWith(SignatureAlgorithm.HS256,"itcast") //头部
                .claim("role","admin"); //添加角色
        System.out.println(jwtBuilder.compact());
    }
}
