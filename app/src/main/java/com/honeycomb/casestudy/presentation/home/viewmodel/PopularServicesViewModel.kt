package com.honeycomb.casestudy.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honeycomb.casestudy.domain.common.BaseResult
import com.honeycomb.casestudy.domain.service.entity.ServiceEntity
import com.honeycomb.casestudy.domain.service.usecase.AllServicesUseCase
import com.honeycomb.casestudy.domain.service.usecase.PopularServicesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularServicesViewModel @Inject constructor(private val popularServicesUseCase: PopularServicesUseCase) : ViewModel(){

    private val state = MutableStateFlow<PopularServicesState>(PopularServicesState.Init)
    val mState: StateFlow<PopularServicesState> get() = state

    private val products = MutableStateFlow<List<ServiceEntity>>(mutableListOf())
    val mUsers: StateFlow<List<ServiceEntity>> get() = products

    private fun isLoading(value : Boolean){
        state.value = PopularServicesState.IsLoading(value)
    }
    private fun isError(value : Boolean){
        state.value = PopularServicesState.IsError(value)
    }
    private fun isErrorMessage(value : String){
        state.value = PopularServicesState.IsErrorMessage(value)
    }

    fun fetchPopularServices(){
        viewModelScope.launch {
            popularServicesUseCase.execute()
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

sealed class PopularServicesState {
    object Init : PopularServicesState()
    data class IsLoading(val isLoading: Boolean) : PopularServicesState()
    data class IsError(val isError: Boolean) : PopularServicesState()
    data class IsErrorMessage(val isErrorValue: String) : PopularServicesState()
}