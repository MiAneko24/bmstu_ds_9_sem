package ru.bmstu.mianeko.personbackend.domain.usecases

import ru.bmstu.mianeko.personbackend.domain.PersonTemplateVerifier
import ru.bmstu.mianeko.personbackend.domain.exceptions.PersonNotExistException
import ru.bmstu.mianeko.personbackend.domain.models.Person
import ru.bmstu.mianeko.personbackend.domain.repository.PersonUpdateRepository

interface PersonUpdateUseCase {
    fun update(person: Person): Person
}

class PersonUpdateUseCaseImpl(
    private val personUpdateRepository: PersonUpdateRepository,
    private val personTemplateVerifier: PersonTemplateVerifier
): PersonUpdateUseCase {
    override fun update(person: Person): Person {
        if (!personUpdateRepository.idExists(person.id)) {
            throw PersonNotExistException(person.id)
        }
        personTemplateVerifier.verify(person)
        return personUpdateRepository.updatePerson(person)
    }
}
