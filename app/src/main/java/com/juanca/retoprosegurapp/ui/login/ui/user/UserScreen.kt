package com.juanca.retoprosegurapp.ui.login.ui.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.juanca.retoprosegurapp.R
import com.juanca.retoprosegurapp.ui.login.domain.model.Customer
import com.juanca.retoprosegurapp.ui.login.domain.model.Employee
import kotlinx.coroutines.launch

@Composable
fun UserScreen(viewModel: UserViewModel) {
    val user by viewModel.user.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        user?.let {
            it.email.also { email ->
                //Uso para debug o dejar logs
                println("email: $email")
            }
            Text(
                text = stringResource(id = R.string.title_name, it.name),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = stringResource(id = R.string.title_edad, it.age),
                style = MaterialTheme.typography.body1
            )
            Text(
                text = stringResource(id = R.string.title_correo, it.email),
                style = MaterialTheme.typography.body1
            )
            Text(
                text = stringResource(id = R.string.title_direccion, it.address),
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(16.dp))

            when (it) {
                is Customer -> {
                    Text(
                        text = stringResource(id = R.string.title_points, it.loyaltyPoints),
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = stringResource(
                            id = R.string.title_fechaRegistro,
                            it.membershipDate
                        ), style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = stringResource(
                            id = R.string.title_preferences,
                            it.preferredCategories.joinToString()
                        ), style = MaterialTheme.typography.body1
                    )
                }

                is Employee -> {
                    Text(
                        text = stringResource(id = R.string.title_id, it.employeeId),
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = stringResource(id = R.string.title_area, it.department),
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = stringResource(id = R.string.title_date_hire, it.hireDate),
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = stringResource(id = R.string.title_cargo, it.jobTitle),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.createCustomer(
                name = "Juan Alfaro",
                age = 26,
                email = "juan.alfaro.fer@gmail.com",
                address = "La Molina",
                loyaltyPoints = 100,
                membershipDate = "2024-08-19",
                preferredCategories = listOf("Software", "Libros")
            )
        }) {
            Text("Create Customer")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            viewModel.createEmployee(
                name = "Luisa Fernandez",
                age = 30,
                email = "sample@gmail.com",
                address = "Bellavista, Callao",
                employeeId = "COD00001",
                department = "Ventas",
                hireDate = "2023-06-01",
                jobTitle = "Ejecutivo de ventas"
            )
        }) {
            Text("Create Employee")
        }
    }
}
