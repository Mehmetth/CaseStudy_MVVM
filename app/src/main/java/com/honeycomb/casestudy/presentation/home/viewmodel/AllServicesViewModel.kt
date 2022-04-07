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
class AllServicesViewModel @Inject constructor(private val allServicesUseCase: AllServicesUseCase) : ViewModel(){

    private val state = MutableStateFlow<ServicesState>(ServicesState.Init)
    val mState: StateFlow<ServicesState> get() = state

    private val products = MutableStateFlow<List<ServiceEntity>>(mutableListOf())
    val mUsers: StateFlow<List<ServiceEntity>> get() = products

    private fun isLoading(value : Boolean){
        state.value = ServicesState.IsLoading(value)
    }
    private fun isError(value : Boolean){
        state.value = ServicesState.IsError(value)
    }
    private fun isErrorMessage(value : String){
        state.value = ServicesState.IsErrorMessage(value)
    }

    fun fetchAllServices(){
        viewModelScope.launch {
            allServicesUseCase.execute()
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

sealed class ServicesState {
    object Init : ServicesState()
    data class IsLoading(val isLoading: Boolean) : ServicesState()
    data class IsError(val isError: Boolean) : ServicesState()
    data class IsErrorMessage(val isErrorValue: String) : ServicesState()
}