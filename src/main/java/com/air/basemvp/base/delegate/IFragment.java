package com.air.basemvp.base.delegate;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.air.basemvp.di.component.AppComponent;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

/**
 * @author Hexl
 * @desc
 * @date 2018/3/30 9:40
 */
public interface IFragment {
    /**
     * 初始化layout布局
     *
     * @param savedInstanceState
     * @return 当返回值不为0时 调用 setContentView方法 并 初始化ButterKnife
     */
    @LayoutRes int initLayout(@Nullable Bundle savedInstanceState);

    /**
     * 注入Dagger 需要在onCreate中执行 ,在绑定layout之后 在onRequest之前调用,
     * 否则调用Presenter会引发NullPointException
     */
    void setDaggerComponent(AppComponent appComponent);

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    void init(@Nullable Bundle savedInstanceState);

    /**
     * 默认在onCreate中调用,请求网络数据.如当前页面无需网络请求,可不实现此方法,
     */
    void onRequest(LifecycleProvider<FragmentEvent> eventLifecycleProvider);

    /**
     * 提供FragmentEvent 用于监听RxFragment的生命周期
     *
     * @return 当前fragment的LifecycleProvider
     */
    LifecycleProvider<FragmentEvent> providerEvent();

    /**
     * 是否使用EventBus
     *
     * @return true 使用 false 不使用
     */
    boolean useEventBus();
}
