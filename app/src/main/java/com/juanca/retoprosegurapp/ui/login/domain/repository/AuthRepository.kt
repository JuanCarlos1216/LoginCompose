package com.juanca.retoprosegurapp.ui.login.domain.repository

interface AuthRepository {
    suspend fun authenticate(email: String, password: String): Boolean
}