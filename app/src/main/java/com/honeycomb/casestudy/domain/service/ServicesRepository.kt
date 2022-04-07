package com.honeycomb.casestudy.domain.service

import com.honeycomb.casestudy.data.service.remote.dto.ServiceDetailResponse
import com.honeycomb.casestudy.data.service.remote.dto.ServicesResponse
import com.honeycomb.casestudy.domain.common.BaseResult
import com.honeycomb.casestudy.domain.service.entity.PostEntity
import com.honeycomb.casestudy.domain.service.entity.ServiceDetailEntity
import com.honeycomb.casestudy.domain.service.entity.ServiceEntity
import kotlinx.coroutines.flow.Flow

interface ServicesRepository {
    suspend fun getAllServices() : Flow<BaseResult<List<ServiceEntity>, ServicesResponse>>
    suspend fun getPopularServices() : Flow<BaseResult<List<ServiceEntity>, ServicesResponse>>
    suspend fun getPosts() : Flow<BaseResult<List<PostEntity>, ServicesResponse>>

    suspend fun getServiceDetail(service_id: String) : Flow<BaseResult<ServiceDetailEntity, ServiceDetailResponse>>
}