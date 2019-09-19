package com.example.postchallenge.di

import com.example.postchallenge.service.PostApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class NetworkModule {

  @ApplicationScope
  @Provides
  fun provideClient(): OkHttpClient = OkHttpClient.Builder().build()

  @ApplicationScope
  @Provides
  fun provideCallAdapterFactory(
    @Named(SchedulerModule.IO_SCHEDULER) ioScheduler: Scheduler
  ): CallAdapter.Factory = RxJava2CallAdapterFactory.createWithScheduler(ioScheduler)

  @ApplicationScope
  @Provides
  fun provideGsonConverterFactory(
    @Named(SerializationModule.GSON_WITH_NULLS) gson: Gson
  ): GsonConverterFactory = GsonConverterFactory.create(gson)

  @ApplicationScope
  @Provides
  fun provideRetrofit(
    client: OkHttpClient,
    callAdapterFactory: CallAdapter.Factory,
    converterFactory: GsonConverterFactory
  ): Retrofit = Retrofit.Builder()
    .baseUrl(DEFAULT_BASE_URL)
    .addCallAdapterFactory(callAdapterFactory)
    .addConverterFactory(converterFactory)
    .client(client)
    .build()

  @ApplicationScope
  @Provides
  fun providePostApi(
    retrofit: Retrofit
  ): PostApi = retrofit.create(PostApi::class.java)

  companion object {
    private const val DEFAULT_BASE_URL = "https://jsonplaceholder.typicode.com/"
  }
}