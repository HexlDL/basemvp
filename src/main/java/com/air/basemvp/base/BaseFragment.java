package com.air.basemvp.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.air.basemvp.App;
import com.air.basemvp.base.delegate.IFragment;
import com.air.basemvp.di.component.AppComponent;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * @author Hexl
 * @desc 基类BaseFragment
 * @date
 */
public abstract class BaseFragment<P extends IPresenter> extends RxFragment implements IFragment, App {
    protected Context mContext;
    protected Activity mActivity;
    private Unbinder mUnbinder;

    @Inject
    protected P mPresenter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
        this.mActivity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = null;
        try {
            int layout = initLayout(savedInstanceState);
            if (layout != 0) {
                //当绑定布局id 不等于时 进行setContentView 和 绑定ButterKnife
                view = inflater != null ? inflater.inflate(layout, container, false) : null;
                if (view != null) {
                    mUnbinder = ButterKnife.bind(this, view);
                    if (useEventBus()) {//默认不进行注册EventBus,只有当复写该方法时并返回true时进行注册
                        EventBus.getDefault().register(this);
                    }
                } else {
                    throw new RuntimeException("Please check bind view is null?");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setDaggerComponent(getAppComponent());
        init(savedInstanceState);
        onRequest(providerEvent());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    @Override
    public AppComponent getAppComponent() {
        return ((App) mActivity.getApplicationContext()).getAppComponent();
    }

    @Override
    public LifecycleProvider<FragmentEvent> providerEvent() {
        return this;
    }

    /**
     * 默认实现不使用EventBus,如果使用请返回true
     *
     * @return
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
        return (T) mActivity.findViewById(id);
    }
}
