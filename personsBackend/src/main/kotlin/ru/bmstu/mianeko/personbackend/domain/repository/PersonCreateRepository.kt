package ru.bmstu.mianeko.personbackend.domain.repository

import ru.bmstu.mianeko.personbackend.domain.models.Person

interface PersonCreateRepository {
    fun generateId(): Int

    fun savePerson(person: Person)
}
