package com.code93.e_movie.data.network

import android.content.Context
import com.code93.e_movie.data.RepositoryImpl
import com.code93.e_movie.data.core.interceptors.AuthInterceptor
import com.code93.e_movie.data.local.dao.TopRatedLocalDao
import com.code93.e_movie.data.local.dao.UpcomingLocalDao
import com.code93.e_movie.domain.Repository
import com.code93.e_movie.ui.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideApplicationContext(activity: MainActivity): Context {
        return activity.applicationContext
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://api.themoviedb.org/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideTheMovieApiService(retrofit: Retrofit): TheMovieApiService {
        return retrofit.create(TheMovieApiService::class.java)
    }

    @Provides
    fun provideRepository(
        apiService: TheMovieApiService,
        topRatedLocalDao: TopRatedLocalDao,
        upcomingLocalDao: UpcomingLocalDao
    ): Repository {
        return RepositoryImpl(apiService, topRatedLocalDao, upcomingLocalDao)
    }
}