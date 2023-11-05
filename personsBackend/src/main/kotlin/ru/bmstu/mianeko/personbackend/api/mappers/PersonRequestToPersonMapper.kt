package ru.bmstu.mianeko.personbackend.api.mappers

import ru.bmstu.mianeko.personbackend.api.models.PersonRequest
import ru.bmstu.mianeko.personbackend.domain.models.Person

class PersonRequestToPersonMapper: (PersonRequest, Int) -> Person {
    override fun invoke(request: PersonRequest, id: Int): Person {
        return request.run {
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
