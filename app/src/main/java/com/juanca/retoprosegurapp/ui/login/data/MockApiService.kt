package com.juanca.retoprosegurapp.ui.login.data

import com.juanca.retoprosegurapp.ui.login.domain.model.AuthResponse
import com.juanca.retoprosegurapp.ui.login.domain.model.Credentials
import retrofit2.Response

class MockApiService: ApiService {
    override suspend fun authenticate(credentials: Credentials): Response<AuthResponse> {
        return Response.success(AuthResponse(success = true, token = "mockToken")) // Simular respuesta exitosa
    }
}