package ru.bmstu.mianeko.personbackend.domain.repository

import ru.bmstu.mianeko.personbackend.domain.models.Person

interface PersonRetrieveRepository {
    fun getPerson(id: Int): Person

    fun getPersonList(): List<Person>
}
