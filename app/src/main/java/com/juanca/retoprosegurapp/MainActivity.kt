package com.juanca.retoprosegurapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.juanca.retoprosegurapp.ui.login.data.di.ServiceModule
import com.juanca.retoprosegurapp.ui.login.ui.login.LoginScreen
import com.juanca.retoprosegurapp.ui.login.ui.login.LoginViewModel
import com.juanca.retoprosegurapp.ui.login.ui.user.UserScreen
import com.juanca.retoprosegurapp.ui.login.ui.user.UserViewModel
import com.juanca.retoprosegurapp.ui.theme.RetoProsegurAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetoProsegurAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val apiService = ServiceModule.provideApiService()
                    val authRepository = ServiceModule.provideAuthRepository(apiService)
                    val authenticateUserUseCase = ServiceModule.provideAuthenticateUserUseCase(authRepository)
                    val viewModel = LoginViewModel(authenticateUserUseCase)
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login"){
                        composable("login") {
                            LoginScreen(viewModel = viewModel, onLoginSuccess = { flag ->
                                if(flag) navController.navigate("user")
                            })
                        }
                        composable("user"){
                            UserScreen(UserViewModel())
                        }
                    }
                }
            }
        }
    }
}
