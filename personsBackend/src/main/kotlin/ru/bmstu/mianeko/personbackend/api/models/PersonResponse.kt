package ru.bmstu.mianeko.personbackend.api.models

data class PersonResponse(
    val id: Int,
    val name: String,
    val age: Int?,
    val address: String?,
    val work: String?
)
