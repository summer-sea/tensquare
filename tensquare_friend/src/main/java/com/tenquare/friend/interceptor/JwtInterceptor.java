package com.tenquare.friend.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-06-01 19:07
 **/

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过拦截器");
        //无论如何都放行，具体能不能操作还是在具体的操作中判断
        //拦截器只是负责把请求头中 包含token的令牌进行一个解析验证
        String header = request.getHeader("Authorization");
        //System.out.println("header1:"+header);
        if(header ==null){
            System.out.println("header2:"+header);
            throw  new RuntimeException("权限不足!");
        }
        if(!header.startsWith("Bearer ")){
            System.out.println("header3:"+header);
            throw  new RuntimeException("权限不足!");
        }

        if(header!=null && !"".equals(header)){
            //如果包含有Authorization头信息，就对其解析
            System.out.println("header4:"+header);
            if( header.startsWith("Bearer ")){
                //System.out.println("header5:"+header);
               //得到token
                String token = header.substring(7);
                //System.out.println("token1:"+token);
                //对令牌进行验证
                try {
                    //System.out.println("roles1:");
                    Claims claims = jwtUtil.parseJWT(token);
                    //System.out.println("roles2:");
                    String roles = (String)claims.get("roles");
                    //System.out.println("roles:"+roles);
                    if(roles !=null && roles.equals("admin")){
                       //取到角色返回到request中
                        //System.out.println("token:"+token);
                        request.setAttribute("claims_admin",claims);
                    }
                    if(roles !=null && roles.equals("user")){
                        //取到角色返回到request中
                        request.setAttribute("claims_user",claims);
                    }
                }catch (Exception e){
                    throw  new RuntimeException("令牌不正确!");
                }
            }
        }
        return true;
    }
}
