package com.honeycomb.casestudy.data.service.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.honeycomb.casestudy.data.service.remote.api.ServiceApi
import com.honeycomb.casestudy.data.service.remote.dto.ServiceDetailResponse
import com.honeycomb.casestudy.data.service.remote.dto.ServicesResponse
import com.honeycomb.casestudy.domain.common.BaseResult
import com.honeycomb.casestudy.domain.service.entity.ServiceEntity
import com.honeycomb.casestudy.domain.service.ServicesRepository
import com.honeycomb.casestudy.domain.service.entity.PostEntity
import com.honeycomb.casestudy.domain.service.entity.ServiceDetailEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ServicesRepositoryImpl @Inject constructor(private val serviceApi: ServiceApi) :
    ServicesRepository {
    override suspend fun getAllServices(): Flow<BaseResult<List<ServiceEntity>, ServicesResponse>> {
        return flow {
            val response = serviceApi.getServices()
            if (response.isSuccessful){
                val body = response.body()!!

                val serviceList = mutableListOf<ServiceEntity>()
                body.serviceResponse.forEach { item ->


                    serviceList.add(ServiceEntity(item.id, item.service_id, item.name, item.long_name, item.image_url, item.pro_count))
                }

                emit(BaseResult.Success(serviceList))
            }else{
                val type = object : TypeToken<ServicesResponse>(){}.type
                val err = Gson().fromJson<ServicesResponse>(response.errorBody()!!.charStream(), type)!!
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun getPopularServices(): Flow<BaseResult<List<ServiceEntity>, ServicesResponse>> {
        return flow {
            val response = serviceApi.getServices()
            if (response.isSuccessful){
                val body = response.body()!!

                val serviceList = mutableListOf<ServiceEntity>()
                body.popular.forEach { item ->
                    serviceList.add(ServiceEntity(item.id, item.service_id, item.name, item.long_name, item.image_url, item.pro_count))
                }

                emit(BaseResult.Success(serviceList))
            }else{
                val type = object : TypeToken<ServicesResponse>(){}.type
                val err = Gson().fromJson<ServicesResponse>(response.errorBody()!!.charStream(), type)!!
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun getPosts(): Flow<BaseResult<List<PostEntity>, ServicesResponse>> {
        return flow {
            val response = serviceApi.getServices()
            if (response.isSuccessful){
                val body = response.body()!!

                val postList = mutableListOf<PostEntity>()
                body.postsResponse.forEach { item ->
                    postList.add(PostEntity(item.title, item.category, item.image_url, item.link))
                }

                emit(BaseResult.Success(postList))
            }else{
                val type = object : TypeToken<ServicesResponse>(){}.type
                val err = Gson().fromJson<ServicesResponse>(response.errorBody()!!.charStream(), type)!!
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun getServiceDetail(service_id: String): Flow<BaseResult<ServiceDetailEntity, ServiceDetailResponse>> {
        return flow {
            val response = serviceApi.getServiceDetail(service_id)
            if (response.isSuccessful){
                val body = response.body()!!

                emit(BaseResult.Success(ServiceDetailEntity(body.id,body.service_id,body.name,body.long_name,body.image_url,body.pro_count,body.average_rating,body.completed_jobs_on_last_month)))
            }else{
                val type = object : TypeToken<ServiceDetailResponse>(){}.type
                val err = Gson().fromJson<ServiceDetailResponse>(response.errorBody()!!.charStream(), type)!!
                emit(BaseResult.Error(err))
            }
        }
    }
}