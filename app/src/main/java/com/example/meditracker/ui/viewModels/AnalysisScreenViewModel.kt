package com.example.meditracker.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditracker.core.ResultOfRequest
import com.example.meditracker.data.api.UserApi
import com.example.meditracker.ui.screens.main.analysis.AnalysisUiState
import com.example.meditracker.utils.USER_UNAUTHORIZED_ERROR_MESSAGE
import com.example.meditracker.utils.toAnalyzesUiStateWithSort
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisScreenViewModel @Inject constructor(
    private val userApi: UserApi,
) : ViewModel() {

    private val _resultOfLoadAnalyzes =
        MutableStateFlow<ResultOfRequest<List<AnalysisUiState>>>(ResultOfRequest.Loading)
    val resultOfLoadAnalyzes = _resultOfLoadAnalyzes.asStateFlow()

    fun loadAnalyzes() {
        viewModelScope.launch {
            userApi.getUserData { user ->
                _resultOfLoadAnalyzes.value = user?.let {
                    ResultOfRequest.Success(it.analyzes.toAnalyzesUiStateWithSort())
                } ?: ResultOfRequest.Error(USER_UNAUTHORIZED_ERROR_MESSAGE)
            }
        }
    }
}