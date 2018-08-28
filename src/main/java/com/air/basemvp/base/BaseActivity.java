package com.air.basemvp.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.air.basemvp.App;
import com.air.basemvp.R;
import com.air.basemvp.base.delegate.IActivity;
import com.air.basemvp.di.component.AppComponent;
import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Hexl
 * @desc 基类BaseActivity
 * @date
 */
public abstract class BaseActivity<P extends IPresenter> extends RxAppCompatActivity implements IActivity, App {
    @Inject
    protected P mPresenter;
    protected Context mContext;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        /*
         * 防止在绑定布局出现异常,这里做健壮性判断
         */
        try {
            int layout = initLayout(savedInstanceState);
            //当绑定布局id 不等于时 进行setContentView 和 绑定ButterKnife
            if (layout != 0) {
                setContentView(layout);
                mUnbinder = ButterKnife.bind(this);
                if (useEventBus()) {//默认不进行注册EventBus,只有当复写该方法时并返回true时进行注册
                    EventBus.getDefault().register(this);
                }
                //设置状态栏颜色和透明度,如果不设置透明度第二个参数传入StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA
                StatusBarUtil.setColor(this, statusBarColor(), statusBarAlpha());
                //禁止横屏(设置为竖屏显示)
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Dagger注入
        setDaggerComponent(getAppComponent());
        //初始化
        init(savedInstanceState);
        //执行网络请求
        onRequest();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 释放资源
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        if (useEventBus()) {//默认不进行反注册EventBus,只有当复写该方法时并返回true时进行反注册
            EventBus.getDefault().unregister(this);
        }
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    /**
     * 提供AppComponent
     */
    @Override
    public AppComponent getAppComponent() {
        return ((App) this.getApplicationContext()).getAppComponent();
    }

    /**
     * 提供Rx生命周期,
     */
    @Override
    public LifecycleProvider<ActivityEvent> providerEvent() {
        return this;
    }

    /**
     * 设置标题栏颜色
     */
    @Override
    public int statusBarColor() {
        return ContextCompat.getColor(this, R.color.statusBarColor);
    }

    /**
     * 设置标题栏透明度
     */
    @Override
    public int statusBarAlpha() {
        return ContextCompat.getColor(this, R.color.statusBarColorAlpha);
    }

    /**
     * 默认实现不使用EventBus,如果使用请返回true
     *
     * @return false 不注册 true 注册
     */
    @Override
    public boolean useEventBus() {
        return false;
    }

    /**
     * 绑定ID 不用强制转换,此方法已废弃,绑定Id请使用ButterKnife
     */
    @Deprecated
    protected <T extends View> T findId(@IdRes int id) {
        return (T) findViewById(id);
    }
}