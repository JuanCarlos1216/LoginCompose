package com.juanca.retoprosegurapp.ui.login.data.repository.auth

import com.juanca.retoprosegurapp.ui.login.data.ApiService
import com.juanca.retoprosegurapp.ui.login.domain.model.Credentials
import com.juanca.retoprosegurapp.ui.login.domain.repository.AuthRepository
import kotlinx.coroutines.delay

class AuthRepositoryImp(private val apiService: ApiService) : AuthRepository {
    override suspend fun authenticate(email: String, password: String): Boolean {
        return try {
            delay(3000)
            val response = apiService.authenticate(Credentials(email, password))
            return response.body()?.success == true
        } catch (e: Exception) {
            false
        }
    }
}