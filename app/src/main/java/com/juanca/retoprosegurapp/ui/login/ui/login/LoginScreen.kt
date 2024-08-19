package com.juanca.retoprosegurapp.ui.login.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juanca.retoprosegurapp.R
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginViewModel, onLoginSuccess: (Boolean) -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Login(Modifier.align(Alignment.Center), viewModel, onLoginSuccess)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel, onLoginSuccess: (Boolean) -> Unit) {

    val email: String by viewModel.email.collectAsState()
    val password: String by viewModel.password.collectAsState()
    val loginEnable: Boolean by viewModel.loginEnabled.collectAsState()
    val isLoading: Boolean by viewModel.isLoading.collectAsState()
    val loginSuccess: Boolean? by viewModel.loginSuccess.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(modifier = modifier) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(email) { viewModel.onLoginChanged(it, password) }
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(password) { viewModel.onLoginChanged(email, it) }
            Spacer(modifier = Modifier.padding(8.dp))
            ForgotPassword(Modifier.align(Alignment.End))
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton(loginEnable) {
                coroutineScope.launch {
                    email.takeIf { it.isNotEmpty() }?.let {
                        val emailLength = it.run {
                            length
                        }
                        if (emailLength > 6) {
                            with(viewModel) {
                                onLoginSelected(email, password)
                            }
                        }
                    }
                }
            }
        }
    }

    loginSuccess?.let { success ->
        LaunchedEffect(success) {
            onLoginSuccess(success)
        }
    }
}

@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF0000FF),
            disabledBackgroundColor = Color(0xFF5353B8),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = loginEnable
    ) {
        Text(text = "Iniciar sesión")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Olvidaste la contraseña?",
        modifier = modifier.clickable { },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF0000FF)
    )
}

@Composable
fun PasswordField(password: String, onTextFieldChange: (String) -> Unit) {
    // Estado para la visibilidad de la contraseña
    val (isPasswordVisible, setIsPasswordVisible) = remember { mutableStateOf(false) }
    val visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()

    TextField(
        value = password,
        onValueChange = { onTextFieldChange(it) },
        placeholder = { Text(text = "Contraseña") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            IconButton(onClick = { setIsPasswordVisible(!isPasswordVisible) }) {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = if (isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                    tint = Color.Gray
                )
            }
        },
        visualTransformation = visualTransformation
    )
}


@Composable
fun EmailField(email: String, onTextFieldChang: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextFieldChang(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img_login),
        contentDescription = "Header",
        modifier = modifier
    )
}
