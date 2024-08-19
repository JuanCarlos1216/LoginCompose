package com.juanca.retoprosegurapp.ui.login.data

import com.juanca.retoprosegurapp.ui.login.domain.model.AuthResponse
import com.juanca.retoprosegurapp.ui.login.domain.model.Credentials
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/authentication")
    suspend fun authenticate(@Body credentials: Credentials): Response<AuthResponse>
}