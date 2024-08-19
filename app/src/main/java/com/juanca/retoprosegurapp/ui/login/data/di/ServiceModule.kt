package com.juanca.retoprosegurapp.ui.login.data.di

import com.juanca.retoprosegurapp.ui.login.data.ApiService
import com.juanca.retoprosegurapp.ui.login.data.MockApiService
import com.juanca.retoprosegurapp.ui.login.data.repository.auth.AuthRepositoryFactory
import com.juanca.retoprosegurapp.ui.login.domain.repository.AuthRepository
import com.juanca.retoprosegurapp.ui.login.domain.usecase.AuthenticateUserUseCase

object ServiceModule {
    fun provideApiService(): ApiService {
        return MockApiService()
    }

    fun provideAuthRepository(apiService: ApiService): AuthRepository {
        return AuthRepositoryFactory.createAuthRepository(apiService)
    }

    fun provideAuthenticateUserUseCase(authRepository: AuthRepository): AuthenticateUserUseCase {
        return AuthenticateUserUseCase(authRepository)
    }
}

