package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.util.RequestContentDataExtractor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-06-04 20:40
 **/
@Component
public class WebFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //得到头信息做转发
        //得到request上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //得到request域
        HttpServletRequest request = currentContext.getRequest();
        String header = request.getHeader("Authorization");
        //判断是否有头信息
        if(header != null && !"".equals(header)){
            //把头信息继续向下传
            currentContext.addZuulRequestHeader("Authorization",header);

        }
        return null;
    }
}
