package com.example.socialcompose.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialcompose.data.SocialRepository
import com.example.socialcompose.model.Social
import com.example.socialcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: SocialRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Social>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Social>>
        get() = _uiState

    fun getSocialById(socialId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderSocialById(socialId))
        }
    }
}