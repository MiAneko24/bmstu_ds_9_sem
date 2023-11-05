package ru.bmstu.mianeko.personbackend.api.mappers
import ru.bmstu.mianeko.personbackend.api.models.PersonResponse
import ru.bmstu.mianeko.personbackend.domain.models.Person


class PersonToPersonResponseMapper: (Person) -> PersonResponse {
    override fun invoke(person: Person): PersonResponse {
        return PersonResponse(
            id = person.id,
            name = person.name,
            age = person.age,
            address = person.address,
            work = person.work
        )
    }

}
