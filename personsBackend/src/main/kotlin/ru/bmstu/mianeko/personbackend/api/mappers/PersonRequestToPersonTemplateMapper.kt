package ru.bmstu.mianeko.personbackend.api.mappers

import ru.bmstu.mianeko.personbackend.api.models.PersonRequest
import ru.bmstu.mianeko.personbackend.domain.models.PersonTemplate

class PersonRequestToPersonTemplateMapper: (PersonRequest) -> PersonTemplate {
    override fun invoke(request: PersonRequest): PersonTemplate {
        return request.run {
            PersonTemplate(
                name = name,
                age = age,
                address = address,
                work = work
            )
        }
    }
}
