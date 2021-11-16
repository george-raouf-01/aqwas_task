package com.example.githubaqwastask.depdencies

import com.example.githubaqwastask.data.mapper.ItemMapper
import com.example.githubaqwastask.data.service.api.ApiService
import com.example.githubaqwastask.domain.use_cases.SortItemsUseCase
import com.example.githubaqwastask.utils.BASE_URL
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun mockApi(): ApiService {

    val httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder()
                .build()
            chain.proceed(newRequest)
        }

    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
    httpClient.addInterceptor(loggingInterceptor)

    val gson = GsonBuilder().setLenient().create()

    val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(httpClient.build())

    val retrofit: Retrofit = retrofitBuilder.build()

    return retrofit.create(ApiService::class.java)

}

fun mockScope() = CoroutineScope(SupervisorJob())

fun mockMapper() = ItemMapper()

fun mockSortItemsUseCase() = SortItemsUseCase(MockRepository())

fun mockRepository() = MockRepository()