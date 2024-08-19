import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.juanca.retoprosegurapp.ui.login.ui.login.LoginViewModel
import com.juanca.retoprosegurapp.ui.login.domain.usecase.AuthenticateUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var authenticateUserUseCase: AuthenticateUserUseCase
    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher) // Configura el dispatcher principal para las pruebas
        MockitoAnnotations.openMocks(this) // Inicializa los mocks

        // Inicializa el caso de uso y el ViewModel
        authenticateUserUseCase = mock()
        loginViewModel = LoginViewModel(authenticateUserUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Resetea el dispatcher principal después de cada prueba
        testDispatcher.cleanupTestCoroutines() // Limpia las corutinas del dispatcher
    }

    @Test
    fun testLoginSuccess(): Unit = runBlocking {
        val username = "testUser"
        val password = "testPassword"
        val expectedResponse = true // Asumiendo que el caso de uso devuelve true

        // Configurando el comportamiento del caso de uso
        whenever(authenticateUserUseCase.execute(username, password)).thenReturn(expectedResponse)

        loginViewModel.onLoginSelected(username, password)

        verify(authenticateUserUseCase).execute(username, password)
    }
}
