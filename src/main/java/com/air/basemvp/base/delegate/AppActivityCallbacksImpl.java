package com.air.basemvp.base.delegate;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.air.basemvp.base.BaseApplication;
import com.air.basemvp.utils.android.AppManagerUtil;
import com.air.basemvp.utils.java.LogUtil;

import java.lang.ref.SoftReference;

/**
 * @author hexl
 * @desc 这里的生命周期会在BaseActivity之前执行, 可以做一些提前初始化的操作
 * 因为java只支持单继承,所以不能达到在集成第三方框架时对其生命周期进行统一管理,
 * ActivityLifecycleCallbacks 在全局 {@link BaseApplication#onCreate()} 中注册
 * @create 2018/3/30 10:16
 */
public class AppActivityCallbacksImpl implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        AppManagerUtil.addCurrentActivity(activity);
        LogUtil.d("onActivityCreated   " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
//        AppManagerUtil.finishActivity(new SoftReference<>(activity));
    }
}
