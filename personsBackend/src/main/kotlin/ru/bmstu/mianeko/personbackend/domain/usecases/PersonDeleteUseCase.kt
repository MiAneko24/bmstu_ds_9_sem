package ru.bmstu.mianeko.personbackend.domain.usecases

import ru.bmstu.mianeko.personbackend.domain.repository.PersonDeleteRepository

interface PersonDeleteUseCase {
    fun delete(personId: Int)
}

internal class PersonDeleteUseCaseImpl(
    private val personDeleteRepository: PersonDeleteRepository
): PersonDeleteUseCase {
    override fun delete(personId: Int) {
        personDeleteRepository.deletePerson(personId)
    }
}
