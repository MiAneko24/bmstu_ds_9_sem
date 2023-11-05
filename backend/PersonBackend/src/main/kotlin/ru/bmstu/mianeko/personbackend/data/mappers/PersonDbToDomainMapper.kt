package ru.bmstu.mianeko.personbackend.data.mappers

import ru.bmstu.mianeko.personbackend.data.models.PersonDb
import ru.bmstu.mianeko.personbackend.domain.models.Person

class PersonDbToDomainMapper: (PersonDb) -> Person {
    override fun invoke(personDb: PersonDb): Person {
        return personDb.run {
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
