package com.air.basemvp;

import com.air.basemvp.base.BaseActivity;
import com.air.basemvp.base.BaseApplication;
import com.air.basemvp.base.BaseFragment;
import com.air.basemvp.di.component.AppComponent;

/**
 * @author Hexl
 * @desc 提供给 {@link BaseApplication},{@link BaseActivity} ,{@link BaseFragment},实现已满足开发规范.
 * @date 2018/3/30 9:07
 */
public interface App {
    AppComponent getAppComponent();
}
