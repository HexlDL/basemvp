package com.air.basemvp.base;

import com.air.basemvp.lifecycle.IRetrofitManager;

/**
 * @author Hexl
 * @desc
 * @date 2018/3/30 16:46
 */
public class BaseModel implements IModel {

    protected IRetrofitManager mRetrofitManager;

    public BaseModel(IRetrofitManager retrofitManager) {
        this.mRetrofitManager = retrofitManager;
    }

    /**
     * 默认在BasePresenter中调用
     */
    @Override
    public void onDestroy() {
        if (mRetrofitManager != null) {
            mRetrofitManager = null;
        }
    }
}