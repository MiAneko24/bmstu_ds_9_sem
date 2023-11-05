package ru.bmstu.mianeko.personbackend.domain.repository

interface PersonDeleteRepository {
    fun deletePerson(id: Int)
}
