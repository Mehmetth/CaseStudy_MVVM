package com.honeycomb.casestudy.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honeycomb.casestudy.domain.common.BaseResult
import com.honeycomb.casestudy.domain.service.entity.ServiceDetailEntity
import com.honeycomb.casestudy.domain.service.entity.ServiceEntity
import com.honeycomb.casestudy.domain.service.usecase.AllServicesUseCase
import com.honeycomb.casestudy.domain.service.usecase.DetailServiceUseCase
import com.honeycomb.casestudy.presentation.home.PopularServicesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailServiceViewModel @Inject constructor(private val detailServiceUseCase: DetailServiceUseCase) : ViewModel(){

    private val state = MutableStateFlow<DetailServicesState>(DetailServicesState.Init)
    val mState: StateFlow<DetailServicesState> get() = state

    private val products = MutableStateFlow<ServiceDetailEntity?>(null)
    val mUsers: StateFlow<ServiceDetailEntity?> get() = products

    private fun isLoading(value : Boolean){
        state.value = DetailServicesState.IsLoading(value)
    }
    private fun isError(value : Boolean){
        state.value = DetailServicesState.IsError(value)
    }
    private fun isErrorMessage(value : String){
        state.value = DetailServicesState.IsErrorMessage(value)
    }
    fun fetchDetailServices(service_id: String){
        viewModelScope.launch {
            detailServiceUseCase.execute(service_id)
                .onStart {
                    isLoading(true)
                    isError(false)
                }
                .catch { exception ->
                    isError(true)
                    isErrorMessage(exception.message.toString())
                }
                .collect { result ->
                    isLoading(false)
                    when(result){
                        is BaseResult.Success -> {
                            products.value = result.data
                            isError(false)
                            isLoading(false)
                        }
                        is BaseResult.Error -> {
                            isError(true)
                            isLoading(false)
                            isErrorMessage(result.toString())
                        }
                    }
                }
        }
    }
}

sealed class DetailServicesState {
    object Init : DetailServicesState()
    data class IsLoading(val isLoading: Boolean) : DetailServicesState()
    data class IsError(val isError: Boolean) : DetailServicesState()
    data class IsErrorMessage(val isErrorValue: String) : DetailServicesState()
}