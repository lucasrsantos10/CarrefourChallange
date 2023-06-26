package com.example.carrefourchallenge.domain.di

import com.example.carrefourchallenge.domain.api.Service
import com.example.carrefourchallenge.domain.api.ServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModules {

    @Provides
    @Singleton
    fun provideService(): Service {
        return ServiceFactory.create(
            Service::class.java,
            "https://api.github.com/"
        )
    }
}