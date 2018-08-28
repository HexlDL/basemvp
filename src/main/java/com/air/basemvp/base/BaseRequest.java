package com.air.basemvp.base;

import com.air.basemvp.http.interceptor.NetWorkInterceptor;

import okhttp3.Interceptor;

/**
 * @author Hexl
 * @desc 不需要传入token的request需要继承BaseRequest并setToken(true)
 * @date 2018/5/21 11:06
 */
public class BaseRequest {

    /**
     * 1 拼接参数
     * 0 不拼接参数
     * 默认返回"1"在{@link NetWorkInterceptor#intercept(Interceptor.Chain)} 进行拼接参数
     */
    private String tokenFlag = "1";

    /**
     * {@link com.air.basemvp.base.BaseRequest {@link #setTokenFlag(String)}}
     *
     * @param tokenFlag
     */
    public void setTokenFlag(String tokenFlag) {
        this.tokenFlag = tokenFlag;
    }
}

