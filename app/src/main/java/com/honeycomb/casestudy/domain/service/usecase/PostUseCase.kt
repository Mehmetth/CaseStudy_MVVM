package com.honeycomb.casestudy.domain.service.usecase

import com.honeycomb.casestudy.data.service.remote.dto.ServicesResponse
import com.honeycomb.casestudy.domain.common.BaseResult
import com.honeycomb.casestudy.domain.service.ServicesRepository
import com.honeycomb.casestudy.domain.service.entity.PostEntity
import com.honeycomb.casestudy.domain.service.entity.ServiceEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostUseCase @Inject constructor(private val servicesRepository: ServicesRepository) {
    suspend fun execute(): Flow<BaseResult<List<PostEntity>, ServicesResponse>> {
        return servicesRepository.getPosts()
    }
}