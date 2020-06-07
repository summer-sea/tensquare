package com.tensquare.manager.filter;

import com.netflix.discovery.converters.Auto;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-06-04 20:13
 **/
@Component
public class ManagerFilter  extends ZuulFilter {


    @Autowired
    private JwtUtil jwtUtil;


    /**
     * 表示在请求前后请求后执行
     * pre post
     * @return
     */
    @Override
    public String filterType() {
        //表示在之前过滤
        return "pre";
    }

    /**
     * 多个执行的顺序 数字越小 越优先
     * @return
     */
    @Override
    public int filterOrder() {
        // 0 表示优先执行
        return 0;
    }

    /**
     * 过滤器是否开启
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器内执行的操作，return 任何 ojebct 的值都表示继续执行
     * setsendzulRespponse（false）表示不再继续执行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("经过了managerfilter  过滤器");
        RequestContext requestContext =RequestContext.getCurrentContext();
        //取得request上下文的域
        HttpServletRequest request = requestContext.getRequest();

        if(request.getMethod().equals("OPTIONS")){
            return  null;
        }

        //登录放行
        if(request.getRequestURL().indexOf("login")>0){
            return  null;
        }


        //得到头信息
        String header = request.getHeader("Authorization");
        if (header != null && !"".equals(header)){
            if (header.startsWith("Bearer ")){
                String token = header.substring(7);
                try {
                    Claims claims =jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if(roles.equals("admin")) {
                        //把头信息转发下去并且放行
                        requestContext.addZuulRequestHeader("Authorization",header);
                        return  null;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    //终止运行
                    requestContext.setSendZuulResponse(false);
                }

            }

        }
        //终止运行
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(403);
        requestContext.setResponseBody("权限不足");
        requestContext.getResponse().setContentType("text/html;charset=utf-8 ");
        return null;
    }
}
