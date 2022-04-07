package com.honeycomb.casestudy.data.service.remote.api

import com.honeycomb.casestudy.data.service.remote.dto.ServiceDetailResponse
import com.honeycomb.casestudy.data.service.remote.dto.ServicesResponse
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path

interface ServiceApi{
    @GET("home")
    suspend fun getServices() : Response<ServicesResponse>

    @GET("service/{service_id}")
    suspend fun getServiceDetail(@Path("service_id") service_id: String) : Response<ServiceDetailResponse>
}