package com.rv.githubapp.di

import com.rv.githubapp.data.remote.api.GithubApi
import com.rv.githubapp.data.remote.model.ContributorsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl(GithubApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            ))
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubApi(retrofit: Retrofit) : GithubApi {
        return retrofit.create(GithubApi::class.java)
    }
}