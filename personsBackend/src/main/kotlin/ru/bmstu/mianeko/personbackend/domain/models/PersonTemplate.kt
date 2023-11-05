package ru.bmstu.mianeko.personbackend.domain.models

open class PersonTemplate(
    open val name: String,
    open val age: Int?,
    open val address: String?,
    open val work: String?
)
