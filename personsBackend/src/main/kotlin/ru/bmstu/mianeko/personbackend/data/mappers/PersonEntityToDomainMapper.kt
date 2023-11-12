package ru.bmstu.mianeko.personbackend.data.mappers

import ru.bmstu.mianeko.personbackend.data.models.PersonEntity
import ru.bmstu.mianeko.personbackend.domain.models.Person

class PersonEntityToDomainMapper: (PersonEntity) -> Person {
    override fun invoke(personEntity: PersonEntity): Person {
        return personEntity.run {
            Person(
                id = id,
                name = name,
                age = age,
                address = address,
                work = work
            )
        }
    }
}
