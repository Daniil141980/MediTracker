package com.example.meditracker.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditracker.R
import com.example.meditracker.core.ResultOfRequest
import com.example.meditracker.data.api.UserAuthenticationApi
import com.example.meditracker.data.repository.UserAnalyzesRepository
import com.example.meditracker.data.repository.UserAppointmentsRepository
import com.example.meditracker.data.repository.UserDiaryRepository
import com.example.meditracker.ui.screens.login.signIn.SignInScreenUiState
import com.example.meditracker.utils.LOADING_DATA_ERROR
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInScreenViewModel @Inject constructor(
    private val userAuthenticationApi: UserAuthenticationApi,
    private val userAnalyzesRepository: UserAnalyzesRepository,
    private val userDiaryRepository: UserDiaryRepository,
    private val userAppointmentsRepository: UserAppointmentsRepository,
) : ViewModel() {

    private val _signInScreenUiState = MutableStateFlow(SignInScreenUiState())
    val signInScreenUiState = _signInScreenUiState.asStateFlow()

    private val _resultOfLoadingData =
        MutableStateFlow<ResultOfRequest<Unit>>(ResultOfRequest.Loading)
    val resultOfLoadingData = _resultOfLoadingData.asStateFlow()

    private var signInJob: Job? = null

    private val _resultOfRequest =
        MutableStateFlow<ResultOfRequest<FirebaseUser>>(ResultOfRequest.Loading)
    val resultOfRequest = _resultOfRequest.asStateFlow()

    fun updateEmail(email: String) {
        _signInScreenUiState.value = signInScreenUiState.value.copy(
            email = email,
        )
        checkEmail()
    }

    fun updatePassword(password: String) {
        _signInScreenUiState.value = signInScreenUiState.value.copy(
            password = password,
        )
        checkPassword()
    }

    private fun checkEmail() {
        val patternEmail = Regex("""^\S+@\S+\.\S+$""")
        _signInScreenUiState.value = signInScreenUiState.value.copy(
            emailErrorMessage = when {
                signInScreenUiState.value.email.isEmpty() -> R.string.empty_field
                !patternEmail.matches(signInScreenUiState.value.email) -> R.string.wrong_email
                else -> null
            }
        )
    }

    private fun checkPassword() {
        _signInScreenUiState.value = signInScreenUiState.value.copy(
            passwordErrorMessage = when {
                signInScreenUiState.value.password.isEmpty() -> R.string.empty_field
                else -> null
            }
        )
    }

    fun signIn() {
        signInJob?.cancel()
        signInJob = viewModelScope.launch {
            val result = userAuthenticationApi.signIn(
                email = signInScreenUiState.value.email,
                password = signInScreenUiState.value.password,
            )
            _resultOfRequest.value = result
        }
    }

    fun startLoadingUserData() {
        viewModelScope.launch {
            userAnalyzesRepository.loadUserAnalyzes()
            userDiaryRepository.loadUserDiaryEntries()
            userAppointmentsRepository.loadUserAppointments()

            combine(
                userAnalyzesRepository.resultOfLoadingAnalyzes,
                userDiaryRepository.resultOfLoadingDiaryEntries,
                userAppointmentsRepository.resultOfLoadingAppointments,
            ) { analyzesResult, diaryResult, appointmentsResult ->
                when {
                    analyzesResult is ResultOfRequest.Error -> ResultOfRequest.Error(analyzesResult.errorMessage)
                    diaryResult is ResultOfRequest.Error -> ResultOfRequest.Error(diaryResult.errorMessage)
                    appointmentsResult is ResultOfRequest.Error -> ResultOfRequest.Error(
                        appointmentsResult.errorMessage
                    )

                    analyzesResult is ResultOfRequest.Success && diaryResult is ResultOfRequest.Success
                            && appointmentsResult is ResultOfRequest.Success -> ResultOfRequest.Success(
                        Unit
                    )

                    else -> ResultOfRequest.Error(LOADING_DATA_ERROR)
                }
            }.collect { combinedResult ->
                _resultOfLoadingData.value = combinedResult
            }
        }
    }

}