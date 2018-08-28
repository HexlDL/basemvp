package com.air.basemvp.di.component;

import android.app.Application;

import com.air.basemvp.di.module.AppModule;
import com.air.basemvp.di.module.ClientModule;
import com.air.basemvp.di.module.GlobalConfigModule;
import com.air.basemvp.lifecycle.IRetrofitManager;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author Hexl
 * @desc 全局app
 * @date
 */
@Singleton
@Component(modules = {AppModule.class, ClientModule.class, GlobalConfigModule.class})
public interface AppComponent {

    Application application();

    IRetrofitManager retrofitManager();

    OkHttpClient okHttpClient();

    Retrofit retrofit();

    Gson gson();

    void inject(Application application);
}
