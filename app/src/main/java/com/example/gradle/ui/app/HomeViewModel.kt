package com.example.gradle.ui.app


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradle.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    var isUserLoggedIn  = MutableStateFlow<Boolean?>(null)

    init {
        observeAuthState()
    }

    private fun observeAuthState() {
        viewModelScope.launch {
            authRepository.getIsLoggedIn()
                .distinctUntilChanged()
                .collect { isLoggedIn ->
                    isUserLoggedIn.emit(isLoggedIn)
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}
