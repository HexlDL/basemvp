package com.air.basemvp.di.module;

import com.air.basemvp.base.BaseApplication;
import com.air.basemvp.http.interceptor.HttpInterceptor;
import com.air.basemvp.http.interceptor.NetWorkInterceptor;
import com.air.basemvp.http.interceptor.RetrofitUrlManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.air.basemvp.AppConfig.BASE_URL;

/**
 * @author Hexl
 * @desc
 * @date 2018/4/2 9:10
 */
@Module
public class ClientModule {

    /**
     * 提供网络拦截器
     *
     * @return 网络拦截器
     */
    @Provides
    NetWorkInterceptor provideNetWorkInterceptor() {
        return new NetWorkInterceptor(BaseApplication.getContext());
    }

    /**
     * 提供日志拦截器
     *
     * @return 日志拦截器
     */
    @Provides
    HttpInterceptor provideHttpInterceptor() {
        return new HttpInterceptor();
    }

    /**
     * 提供 RetrofitBuilder
     *
     * @return
     */
    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    /**
     * 提供 GsonBuilder
     *
     * @return
     */
    @Singleton
    @Provides
    GsonBuilder provideGsonBuilder() {
        return new GsonBuilder();
    }

    /**
     * 提供Gson
     *
     * @param builder
     * @return 自定义一些Gson的配置
     */
    @Singleton
    @Provides
    Gson provideGson(GsonBuilder builder) {
        return builder
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
//                .setPrettyPrinting()
                .serializeNulls()//支持null值
                .enableComplexMapKeySerialization()//支持Object为key的map对象,默认只支持String为key的map
                .create();
    }


    /**
     * 提供OkHttpClient
     *
     * @param netWorkInterceptor 拦截器
     * @return OkHttpClient
     */
    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(NetWorkInterceptor netWorkInterceptor, HttpInterceptor httpInterceptor) {
        int TIME_OUT = 10;
        return RetrofitUrlManager.getInstance().with(new OkHttpClient.Builder())
                //.addInterceptor(httpInterceptor.logInterceptor())//Log日志拦截器
                .addNetworkInterceptor(netWorkInterceptor)//网络请求拦截器
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)//连接超时时间
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)//写操作 超时时间
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)//读操作超时时间
                .retryOnConnectionFailure(true)//错误重连
                .build();
    }

    /**
     * 提供Retrofit
     *
     * @param client
     * @param builder
     * @param gson
     * @return
     */
    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client, Retrofit.Builder builder, Gson gson) {

        return builder
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加RxJava2
                .addConverterFactory(GsonConverterFactory.create(gson))//添加Gson解析器
                .build();
    }

}
