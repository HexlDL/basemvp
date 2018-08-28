package com.air.basemvp.di.module;

import android.app.Application;

import com.air.basemvp.lifecycle.IRetrofitManager;
import com.air.basemvp.lifecycle.RetrofitManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *@desc
 *@author Hexl
 *@date
 */
@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    Application providesApplication() {
        return mApplication;
    }

    /**
     * 提供RepositoryManager(方法一)
     * 需要在实现类的构造函数中加入@Inject注解,并将其Module更改为abstract抽象类
     * 这种方法是在dagger2.10提供的,在之前版本中没有,并且必须包含静态方法
     *
     * @param repositoryManager
     * @return
     */
//    @Binds
//    protected abstract IRetrofitManager bindRepositoryManager(RetrofitManagerImpl repositoryManager);

    /**
     * 提供RepositoryManager(方法二)
     * 需要在构造函数中加入@Inject 注解,
     *
     * @param retrofitManager
     * @return
     */
    @Provides
    IRetrofitManager providersRetrofitManager(RetrofitManagerImpl retrofitManager) {
        return retrofitManager;
    }
}
