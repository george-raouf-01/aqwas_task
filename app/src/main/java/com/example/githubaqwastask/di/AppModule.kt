package com.example.githubaqwastask.di


import com.example.githubaqwastask.data.mapper.ItemMapper
import com.example.githubaqwastask.data.repositories.ItemRepositoryImpl
import com.example.githubaqwastask.data.service.api.ApiService
import com.example.githubaqwastask.domain.repositories.ItemRepository
import com.example.githubaqwastask.utils.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @ApplicationScope
    @Provides
    @Singleton
    fun providesApplicationScope() = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun providesOkHttpClientBuilder(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).build()


    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun providesApiEndPoint(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesItemRepository(@ApplicationScope applicationScope: CoroutineScope,
                                mapper: ItemMapper, apiService: ApiService): ItemRepository =
        ItemRepositoryImpl(applicationScope = applicationScope, mapper =  mapper, apiService = apiService)
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope