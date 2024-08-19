package com.juanca.retoprosegurapp.ui.login.domain.usecase

import com.juanca.retoprosegurapp.ui.login.domain.repository.AuthRepository

class AuthenticateUserUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String): Boolean {
        return authRepository.authenticate(email, password)
    }
}