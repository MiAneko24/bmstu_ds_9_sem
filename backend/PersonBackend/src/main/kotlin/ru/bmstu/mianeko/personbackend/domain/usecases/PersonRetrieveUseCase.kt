package ru.bmstu.mianeko.personbackend.domain.usecases

import ru.bmstu.mianeko.personbackend.domain.models.Person
import ru.bmstu.mianeko.personbackend.domain.repository.PersonRetrieveRepository

interface PersonRetrieveUseCase {
    fun getPerson(id: Int): Person
    fun getPersonList(): List<Person>
}

class PersonRetrieveUseCaseImpl(
    private val personRetrieveRepository: PersonRetrieveRepository
): PersonRetrieveUseCase {
    override fun getPerson(id: Int): Person {
        return personRetrieveRepository.getPerson(id)
    }

    override fun getPersonList(): List<Person> {
        return personRetrieveRepository.getPersonList()
    }
}
