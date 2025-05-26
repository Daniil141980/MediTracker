package com.example.meditracker.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditracker.core.ResultOfRequest
import com.example.meditracker.data.repository.UserDiaryRepository
import com.example.meditracker.domain.model.DiaryEntry
import com.example.meditracker.presenter.DiaryProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryEntryDetailsScreenViewModel @Inject constructor(
    private val userDiaryRepository: UserDiaryRepository,
) : ViewModel() {

    private val _diaryEntry = MutableStateFlow(DiaryEntry())
    val diaryEntry = _diaryEntry.asStateFlow()

    private var deletionDiaryEntryJob: Job? = null

    private val _resultOfDeletionDiaryEntry =
        MutableStateFlow<ResultOfRequest<Unit>>(ResultOfRequest.Loading)
    val resultOfDeletionDiaryEntry = _resultOfDeletionDiaryEntry.asStateFlow()

    init {
        viewModelScope.launch {
            DiaryProvider.diaryEntry.collect {
                _diaryEntry.value = it
            }
        }
    }

    fun deleteDiaryEntry() {
        deletionDiaryEntryJob?.cancel()
        deletionDiaryEntryJob = viewModelScope.launch {
            userDiaryRepository.deleteDiaryEntry(diaryEntry.value.index)
            userDiaryRepository.resultOfDeletionDiaryEntry.collect {
                _resultOfDeletionDiaryEntry.value = it
            }
        }
    }
}