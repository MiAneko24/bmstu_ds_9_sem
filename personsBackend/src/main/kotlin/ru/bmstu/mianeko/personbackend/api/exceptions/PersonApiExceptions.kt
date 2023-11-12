package ru.bmstu.mianeko.personbackend.api.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

open class ApiException(
    override val message: String?
): RuntimeException(message)

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Person with requested id was not found")
class PersonNotFoundException(
    override val message: String?
) : ApiException(message)

