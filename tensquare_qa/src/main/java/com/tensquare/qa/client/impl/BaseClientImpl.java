package com.tensquare.qa.client.impl;

import com.tensquare.qa.client.BaseClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-06-04 18:51
 **/

@Component
public class BaseClientImpl implements BaseClient {

    @Override
    public Result findById(String labelId) {
        return new Result(false, StatusCode.ERROR,"熔断器出发了");
    }
}
