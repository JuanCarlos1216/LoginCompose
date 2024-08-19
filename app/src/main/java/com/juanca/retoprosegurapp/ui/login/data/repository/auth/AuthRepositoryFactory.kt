package com.juanca.retoprosegurapp.ui.login.data.repository.auth

import com.juanca.retoprosegurapp.ui.login.data.ApiService
import com.juanca.retoprosegurapp.ui.login.domain.repository.AuthRepository

object AuthRepositoryFactory {
    fun createAuthRepository(apiService: ApiService): AuthRepository {
        return AuthRepositoryImp(apiService)
    }
}