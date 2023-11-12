package ru.bmstu.mianeko.personbackend.domain.usecases

import ru.bmstu.mianeko.personbackend.domain.PersonTemplateVerifier
import ru.bmstu.mianeko.personbackend.domain.models.Person
import ru.bmstu.mianeko.personbackend.domain.models.PersonTemplate
import ru.bmstu.mianeko.personbackend.domain.repository.PersonCreateRepository

interface PersonCreateUseCase {
    fun create(personTemplate: PersonTemplate): Int
}

class PersonCreateUseCaseImpl(
    private val personCreateRepository: PersonCreateRepository,
    private val personTemplateVerifier: PersonTemplateVerifier,

): PersonCreateUseCase {
    override fun create(personTemplate: PersonTemplate): Int {
        personTemplateVerifier.verify(personTemplate)
        val newId = personCreateRepository.generateId()
        val person = personTemplate.run {
            Person(
                id = newId,
                name = name,
                age = age,
                address = address,
                work = work
            )
        }
        personCreateRepository.savePerson(person)
        return person.id
    }
}
