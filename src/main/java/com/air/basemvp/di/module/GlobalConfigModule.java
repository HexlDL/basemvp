package com.air.basemvp.di.module;

import com.air.basemvp.http.file.download.FileDownload;
import com.air.basemvp.http.file.upload.FileUpload;
import com.air.basemvp.lifecycle.RetrofitManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GlobalConfigModule {

    @Singleton
    @Provides
    public FileUpload provideFileUpload(RetrofitManagerImpl manager) {
        return new FileUpload(manager);
    }

    @Singleton
    @Provides
    public FileDownload provideFileDownload(RetrofitManagerImpl manager) {
        return new FileDownload(manager);
    }

}
