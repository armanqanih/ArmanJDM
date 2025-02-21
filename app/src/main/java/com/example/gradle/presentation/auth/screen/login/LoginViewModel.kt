package com.example.gradle.presentation.auth.screen.login

import androidx.lifecycle.ViewModel

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
//    val loginUseCase: LoginUseCase,

) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onUsernameChanged(username: String) {
        _uiState.value = _uiState.value.copy(username = username)
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }



    fun login() {
        if (_uiState.value.username.isBlank() || _uiState.value.password.isBlank()) {
            _uiState.value = _uiState.value.copy(errorMessage = "Username and password must not be empty")
            return
        }

        _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

//        viewModelScope.launch {
//            loginUseCase.invoke(_uiState.value.username, _uiState.value.password , "android").collect {
//                when (it) {
//                    is ResultState.Success -> {
//                        _uiState.value = _uiState.value.copy(isLoading = false, isLoggedIn = true)
//                        isUSerLogged=true
//
//                    }
//                    is ResultState.Error -> {
//                        it.error
//                        _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = it.error.message)
//                    }
//                }
//            }
//
//        }
    }
}

