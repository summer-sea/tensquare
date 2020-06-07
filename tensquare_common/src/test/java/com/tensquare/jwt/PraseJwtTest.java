package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.junit.Test;

import java.text.SimpleDateFormat;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-06-01 17:38
 **/

public class PraseJwtTest {
    @Test
    public  void prase() {
        //解析完相当于map
        Claims claims = Jwts.parser().setSigningKey("itcast")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9." +
                        "eyJqdGkiOiI2NjYiLCJzdWIiOiLlsI" +
                        "_pqawiLCJpYXQiOjE1OTEwMDUxODQsImV4cCI6MTU5MTAwNTI0NCwicm9sZSI6ImFkbWluIn0.UPsyZxx2w8OjFPc0ldjgz7Wf7w8KlKoO15zY8dnnBHg")
                .getBody();
        System.out.println("用户id :"+claims.getId());
        System.out.println("用户名 :"+claims.getSubject());
        System.out.println("用户角色 :"+claims.get("role"));
        System.out.println("登陆时间 :"+new SimpleDateFormat("yyy-mm-dd hh:mm:ss")
                .format(claims.getIssuedAt()));
        System.out.println("过期时间 :"+new SimpleDateFormat("yyy-mm-dd hh:mm:ss")
                .format(claims.getExpiration()));

    }
}
