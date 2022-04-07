package com.honeycomb.casestudy.domain.service.usecase

import com.honeycomb.casestudy.data.service.remote.dto.ServiceDetailResponse
import com.honeycomb.casestudy.data.service.remote.dto.ServicesResponse
import com.honeycomb.casestudy.domain.common.BaseResult
import com.honeycomb.casestudy.domain.service.ServicesRepository
import com.honeycomb.casestudy.domain.service.entity.ServiceDetailEntity
import com.honeycomb.casestudy.domain.service.entity.ServiceEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailServiceUseCase @Inject constructor(private val servicesRepository: ServicesRepository) {
    suspend fun execute(service_id: String): Flow<BaseResult<ServiceDetailEntity, ServiceDetailResponse>> {
        return servicesRepository.getServiceDetail(service_id)
    }
}