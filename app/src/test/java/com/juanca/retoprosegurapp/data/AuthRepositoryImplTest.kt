package com.juanca.retoprosegurapp.data

import com.juanca.retoprosegurapp.ui.login.data.ApiService
import com.juanca.retoprosegurapp.ui.login.data.repository.auth.AuthRepositoryImp
import com.juanca.retoprosegurapp.ui.login.domain.model.AuthResponse
import com.juanca.retoprosegurapp.ui.login.domain.model.Credentials
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlinx.coroutines.runBlocking
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AuthRepositoryImplTest {

    @Mock
    lateinit var mockApiService: ApiService

    @InjectMocks
    lateinit var authRepository: AuthRepositoryImp

    @Test
    fun `test authenticate success`() = runBlocking {
        val email = "test@gmail.com"
        val password = "1234567"
        val mockResponse = Response.success(AuthResponse(true)) // Suponiendo que AuthResponse tiene un campo isSuccessful

        Mockito.`when`(mockApiService.authenticate(Credentials(email, password)))
            .thenReturn(mockResponse)

        val result = authRepository.authenticate(email, password)

        assertTrue(result)
    }

    @Test
    fun `test authenticate failure`() = runBlocking {
        val email = "test@gmail.com"
        val password = "1234567"
        val mockResponse = Response.success(AuthResponse(false))

        Mockito.`when`(mockApiService.authenticate(Credentials(email, password)))
            .thenReturn(mockResponse)

        val result = authRepository.authenticate(email, password)

        assertFalse(result)
    }
}
