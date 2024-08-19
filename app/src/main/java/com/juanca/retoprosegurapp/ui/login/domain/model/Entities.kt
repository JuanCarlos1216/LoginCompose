package com.juanca.retoprosegurapp.ui.login.domain.model

open class User {
    var name: String = ""
    var age: Int = 0
    var email: String = ""
    var address: String = ""
}

class Customer: User() {
    var loyaltyPoints: Int = 0
    var membershipDate: String = ""
    var preferredCategories: List<String> = listOf()
}

class Employee: User(){
    var employeeId: String = ""
    var department: String = ""
    var hireDate: String = ""
    var jobTitle: String = ""
}

data class Credentials(
    val email: String,
    val password: String
)

data class AuthResponse(
    val success: Boolean,
    val token: String? = null,
    val message: String? = null
)