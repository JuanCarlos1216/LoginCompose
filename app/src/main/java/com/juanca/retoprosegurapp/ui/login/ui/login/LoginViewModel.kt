package com.juanca.retoprosegurapp.ui.login.ui.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanca.retoprosegurapp.ui.login.domain.repository.AuthRepository
import com.juanca.retoprosegurapp.ui.login.domain.usecase.AuthenticateUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class LoginViewModel(private val authenticateUserUseCase: AuthenticateUserUseCase): ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _loginEnabled = MutableStateFlow(false)
    val loginEnabled: StateFlow<Boolean> = _loginEnabled.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _loginSuccess = MutableStateFlow<Boolean?>(null)
    val loginSuccess: StateFlow<Boolean?> = _loginSuccess.asStateFlow()

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnabled.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 6

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    suspend fun onLoginSelected(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val success = authenticateUserUseCase.execute(email, password)
            _loginSuccess.value = success
            _isLoading.value = false
        }
    }
}