package ru.bmstu.mianeko.personbackend.api.models

data class PersonRequest(
    val name: String,
    val age: Int?,
    val address: String?,
    val work: String?
)
