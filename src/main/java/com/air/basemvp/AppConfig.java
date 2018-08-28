package com.air.basemvp;

/**
 * @author Hexl
 * @desc App全局统一配置, 这里包含了, 是否开启debug模式, 打印log ,URL地址
 * 所有依赖本Module的模块可以更改BASE_URL,建议在common里的Application进行更改,这里默认为上线app地址
 */
public class AppConfig {
    /**
     * true Debug 版
     * false Release 版
     */
    public static final boolean isDebug = true;
    /**
     * 由于Retrofit必须指定BaseURL所以在这里临时定义,在Common中定义了新BaseURL
     */
    public static String BASE_URL = "http://192.168.1.40:18000/";
    /**
     * 文件服务器地址
     */
    public static String BASE_FILE_URL = "http://192.168.1.40:9011/";
    /**
     * 保存在sp中的name
     */
    public static final String PROJECT_NAME = "AirTime";
}