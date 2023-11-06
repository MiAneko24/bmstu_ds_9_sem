package ru.bmstu.mianeko.personbackend.domain.repository

import ru.bmstu.mianeko.personbackend.domain.models.Person

interface PersonUpdateRepository {
    fun idExists(id: Int): Boolean

    fun savePerson(person: Person): Person
}
