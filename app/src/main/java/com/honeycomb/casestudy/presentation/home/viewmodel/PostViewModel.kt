package com.honeycomb.casestudy.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honeycomb.casestudy.domain.common.BaseResult
import com.honeycomb.casestudy.domain.service.entity.PostEntity
import com.honeycomb.casestudy.domain.service.entity.ServiceEntity
import com.honeycomb.casestudy.domain.service.usecase.PopularServicesUseCase
import com.honeycomb.casestudy.domain.service.usecase.PostUseCase
import com.honeycomb.casestudy.presentation.home.PopularServicesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel  @Inject constructor(private val postUseCase: PostUseCase) : ViewModel(){

    private val state = MutableStateFlow<PostState>(PostState.Init)
    val mState: StateFlow<PostState> get() = state

    private val products = MutableStateFlow<List<PostEntity>>(mutableListOf())
    val mUsers: StateFlow<List<PostEntity>> get() = products

    private fun isLoading(value : Boolean){
        state.value = PostState.IsLoading(value)
    }
    private fun isError(value : Boolean){
        state.value = PostState.IsError(value)
    }
    private fun isErrorMessage(value : String){
        state.value = PostState.IsErrorMessage(value)
    }

    fun fetchPosts(){
        viewModelScope.launch {
            postUseCase.execute()
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

sealed class PostState {
    object Init : PostState()
    data class IsLoading(val isLoading: Boolean) : PostState()
    data class IsError(val isError: Boolean) : PostState()
    data class IsErrorMessage(val isErrorValue: String) : PostState()
}