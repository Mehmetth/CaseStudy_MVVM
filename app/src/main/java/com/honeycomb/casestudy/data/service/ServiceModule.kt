package com.honeycomb.casestudy.data.service

import com.honeycomb.casestudy.data.common.NetworkModule
import com.honeycomb.casestudy.data.service.remote.api.ServiceApi
import com.honeycomb.casestudy.data.service.repository.ServicesRepositoryImpl
import com.honeycomb.casestudy.domain.service.ServicesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Singleton
    @Provides
    fun provideProductApi(retrofit: Retrofit) : ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideProductRepository(serviceApi: ServiceApi) : ServicesRepository {
        return ServicesRepositoryImpl(serviceApi)
    }
}