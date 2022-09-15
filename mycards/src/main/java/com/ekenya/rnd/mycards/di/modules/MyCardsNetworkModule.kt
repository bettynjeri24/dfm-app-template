package com.ekenya.rnd.mycards.di.modules

import com.ekenya.rnd.mycards.BuildConfig
import com.ekenya.rnd.mycards.data.network.MyCardsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MyCardsNetworkModule {

    @Provides
    fun providesOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }


    @Provides
    fun providesApiService(httpClient: OkHttpClient): MyCardsApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(
                MyCardsApi::class.java
            )
    }

}
