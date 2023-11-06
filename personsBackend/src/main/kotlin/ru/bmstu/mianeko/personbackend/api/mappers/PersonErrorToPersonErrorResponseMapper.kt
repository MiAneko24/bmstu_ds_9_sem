package ru.bmstu.mianeko.personbackend.api.mappers

import ru.bmstu.mianeko.personbackend.api.models.PersonErrorResponse
import ru.bmstu.mianeko.personbackend.domain.models.PersonError

class PersonErrorToPersonErrorResponseMapper: (PersonError) -> PersonErrorResponse {
    override fun invoke(personError: PersonError): PersonErrorResponse {
        return personError.run {
            PersonErrorResponse(
                message = message,
                errors = errors
            )
        }
    }
}
