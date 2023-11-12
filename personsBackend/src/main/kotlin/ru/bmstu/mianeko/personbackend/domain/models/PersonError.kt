package ru.bmstu.mianeko.personbackend.domain.models

data class PersonError(
    val message: String,
    val errors: Map<String, String>
)
