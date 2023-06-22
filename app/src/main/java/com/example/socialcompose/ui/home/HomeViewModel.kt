package com.example.socialcompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialcompose.data.SocialRepository
import com.example.socialcompose.model.Social
import com.example.socialcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: SocialRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Social>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Social>>>
        get() = _uiState

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    fun search(query: String) {
        _query.value = query
        viewModelScope.launch {
            repository.searchByName(query)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { social ->
                    _uiState.value = UiState.Success(social)
                }

        }
    }

    fun getAllSocials() {
        viewModelScope.launch {
            repository.getAllSocials()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { social ->
                    _uiState.value = UiState.Success(social)
                }

        }
    }
}