package com.uiapp.thuctap.injection.module;


import com.uiapp.thuctap.AppConstants;
import com.uiapp.thuctap.BuildConfig;
import com.uiapp.thuctap.interactor.api.ApiManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiManager provideApiManager(Retrofit retrofit) {
        return new ApiManager(retrofit);
    }
}
