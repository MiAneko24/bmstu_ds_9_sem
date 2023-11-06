package ru.bmstu.mianeko.personbackend.api.exceptions

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import ru.bmstu.mianeko.personbackend.api.mappers.PersonErrorToPersonErrorResponseMapper
import ru.bmstu.mianeko.personbackend.api.models.PersonErrorResponse
import ru.bmstu.mianeko.personbackend.domain.exceptions.PersonArgumentsException

@ControllerAdvice
class PersonExceptionsHandler(
    @Autowired private val mapper: PersonErrorToPersonErrorResponseMapper
) {

    @ExceptionHandler(PersonArgumentsException::class)
    @ResponseBody
    fun personArgumentsException(
        exception: PersonArgumentsException
    ): ResponseEntity<PersonErrorResponse> {
        return ResponseEntity
            .badRequest()
            .body(mapper(exception.personError))
    }
}
