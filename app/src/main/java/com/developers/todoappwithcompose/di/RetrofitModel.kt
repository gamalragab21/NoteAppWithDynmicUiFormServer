package com.developers.todoappwithcompose.di

import com.developers.todoappwithcompose.data.ApiNoteService
import com.developers.todoappwithcompose.helpers.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
object RetrofitModel {



//    @Provides
//    @ViewModelScoped
//    @Token
//    fun provideTokenUser(dataStoreManager: DataStoreManager):String = dataStoreManager.glucoseFlow.value?.token?:""
//
//
//
//    // TODO: 11/8/2021  For implementation Moshi
//
//    @Provides
//    @ViewModelScoped
//    fun providesMoshi(): Moshi = Moshi
//        .Builder()
//        .add(KotlinJsonAdapterFactory())
//        .build()
//
//
//    // TODO: 11/8/2021  For implementation OkHttpClient

    @Provides
    @ViewModelScoped
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val localHttpLoggingInterceptor = HttpLoggingInterceptor()
        localHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return localHttpLoggingInterceptor
    }

    @Provides
    @ViewModelScoped
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .build()


    // TODO: 11/8/2021  For implementation Retrofit

    @Provides
    @ViewModelScoped
    fun providesApiService(okHttpClient: OkHttpClient): ApiNoteService =
        Retrofit.Builder()
            .run {
                baseUrl(Constants.BASE_URL)
                client(okHttpClient)
                addConverterFactory(GsonConverterFactory.create())
                build()
            }.create(ApiNoteService::class.java)

}