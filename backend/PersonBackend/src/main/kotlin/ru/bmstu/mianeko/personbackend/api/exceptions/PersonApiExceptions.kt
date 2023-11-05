package ru.bmstu.mianeko.personbackend.api.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import ru.bmstu.mianeko.personbackend.domain.exceptions.PersonArgumentsException
import ru.bmstu.mianeko.personbackend.domain.models.PersonError

open class ApiException(
    override val message: String?
): RuntimeException(message)

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Person with requested id was not found")
class PersonNotFoundException(
    override val message: String?
) : ApiException(message)

@ExceptionHandler(PersonArgumentsException::class)
@ResponseBody
fun personArgumentsException(exception: PersonArgumentsException): ResponseEntity<PersonError> {
    return ResponseEntity
        .badRequest()
        .body(exception.personError)
}
