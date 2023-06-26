package com.example.carrefourchallenge.domain.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceFactory {
    companion object {
        fun <T> create(
            serviceClass: Class<T>,
            url: String
        ): T {
            val retrofit = getRetrofit(url)
            return retrofit.create(serviceClass)
        }

        private fun getRetrofit(url: String): Retrofit {
            val okHttpClient = OkHttpClient.Builder().readTimeout(1, TimeUnit.MINUTES).build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}