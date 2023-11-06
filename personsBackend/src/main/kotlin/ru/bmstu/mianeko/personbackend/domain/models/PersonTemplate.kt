package ru.bmstu.mianeko.personbackend.domain.models

open class PersonTemplate(
    open val name: String,
    open val age: Int? = null,
    open val address: String? = null,
    open val work: String? = null
)
