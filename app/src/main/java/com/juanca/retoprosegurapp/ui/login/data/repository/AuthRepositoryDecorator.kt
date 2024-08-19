package com.juanca.retoprosegurapp.ui.login.data.repository

import com.juanca.retoprosegurapp.ui.login.domain.repository.AuthRepository

class AuthRepositoryDecorator(private val authRepository: AuthRepository) : AuthRepository {
    override suspend fun authenticate(email: String, password: String): Boolean {
        // Agregar lógica adicional si es necesario
        return authRepository.authenticate(email, password)
    }
}