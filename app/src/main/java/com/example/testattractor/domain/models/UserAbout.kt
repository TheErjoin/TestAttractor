package com.example.testattractor.domain.models

data class UserAbout(
    val user: User
)

data class User(
    val education: String,
    val first_name: String,
    val photo: String,
    val second_name: String,
    val company: List<Company>,
)

data class Company(
    val name: String,
    val position: String
)