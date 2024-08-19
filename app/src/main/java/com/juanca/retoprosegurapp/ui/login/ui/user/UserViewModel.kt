package com.juanca.retoprosegurapp.ui.login.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanca.retoprosegurapp.ui.login.domain.model.Customer
import com.juanca.retoprosegurapp.ui.login.domain.model.Employee
import com.juanca.retoprosegurapp.ui.login.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    fun createCustomer(name: String, age: Int, email: String, address: String, loyaltyPoints: Int, membershipDate: String, preferredCategories: List<String>) {
        viewModelScope.launch {
            // Aquí se podría llamar a un repositorio para almacenar los datos si es necesario
            _user.value = Customer().apply {
                this.name = name
                this.age = age
                this.email = email
                this.address = address
                this.loyaltyPoints = loyaltyPoints
                this.membershipDate = membershipDate
                this.preferredCategories = preferredCategories
            }
        }
    }

    fun createEmployee(name: String, age: Int, email: String, address: String, employeeId: String, department: String, hireDate: String, jobTitle: String) {
        viewModelScope.launch {
            _user.value = Employee().apply {
                this.name = name
                this.age = age
                this.email = email
                this.address = address
                this.employeeId = employeeId
                this.department = department
                this.hireDate = hireDate
                this.jobTitle = jobTitle
            }
        }
    }
}