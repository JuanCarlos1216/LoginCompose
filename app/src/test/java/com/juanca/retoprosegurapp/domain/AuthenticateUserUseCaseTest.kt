package com.juanca.retoprosegurapp.domain

import com.juanca.retoprosegurapp.ui.login.domain.repository.AuthRepository
import com.juanca.retoprosegurapp.ui.login.domain.usecase.AuthenticateUserUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class AuthenticateUserUseCaseTest {

    private lateinit var authenticateUserUseCase: AuthenticateUserUseCase
    private val mockAuthRepository = mock(AuthRepository::class.java)

    @Before
    fun setUp() {
        authenticateUserUseCase = AuthenticateUserUseCase(mockAuthRepository)
    }

    @Test
    fun `test authentication success`() = runBlocking {
        // Configura el comportamiento del mock
        `when`(mockAuthRepository.authenticate("email", "password")).thenReturn(true)

        val result = authenticateUserUseCase.execute("email", "password")

        assertTrue(result)
    }

}
