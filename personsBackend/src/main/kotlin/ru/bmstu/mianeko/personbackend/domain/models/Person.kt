package ru.bmstu.mianeko.personbackend.domain.models

data class Person(
    val id: Int,
    override val name: String,
    override val age: Int?,
    override val address: String?,
    override val work: String?
): PersonTemplate(name, age, address, work)
