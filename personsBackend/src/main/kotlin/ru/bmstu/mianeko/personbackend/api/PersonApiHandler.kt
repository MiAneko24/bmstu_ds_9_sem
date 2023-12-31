package ru.bmstu.mianeko.personbackend.api

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.bmstu.mianeko.personbackend.api.exceptions.PersonNotFoundException
import ru.bmstu.mianeko.personbackend.api.mappers.PersonRequestToPersonMapper
import ru.bmstu.mianeko.personbackend.api.mappers.PersonRequestToPersonTemplateMapper
import ru.bmstu.mianeko.personbackend.api.mappers.PersonToPersonResponseMapper
import ru.bmstu.mianeko.personbackend.api.models.PersonRequest
import ru.bmstu.mianeko.personbackend.api.models.PersonResponse
import ru.bmstu.mianeko.personbackend.domain.exceptions.PersonNotExistException
import ru.bmstu.mianeko.personbackend.domain.usecases.PersonCreateUseCase
import ru.bmstu.mianeko.personbackend.domain.usecases.PersonDeleteUseCase
import ru.bmstu.mianeko.personbackend.domain.usecases.PersonRetrieveUseCase
import ru.bmstu.mianeko.personbackend.domain.usecases.PersonUpdateUseCase

@RestController
@RequestMapping("/persons")
class PersonApiHandler(
    private val personRetrieveUseCase: PersonRetrieveUseCase,
    private val personCreateUseCase: PersonCreateUseCase,
    private val personUpdateUseCase: PersonUpdateUseCase,
    private val personDeleteUseCase: PersonDeleteUseCase,
    private val personRequestToPersonTemplateMapper: PersonRequestToPersonTemplateMapper,
    private val personRequestToPersonMapper: PersonRequestToPersonMapper,
    private val personToResponseMapper: PersonToPersonResponseMapper
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/{personId}")
    fun getPerson(
        @PathVariable personId: Int
    ): PersonResponse {
        try {
            val person = personRetrieveUseCase.getPerson(personId)
            log.debug("Got person {}", person)
            return personToResponseMapper(person)
        } catch (e: PersonNotExistException) {
            throw PersonNotFoundException(e.message)
        }
    }

    @GetMapping
    fun getPersonList(): List<PersonResponse> {
        val personList = personRetrieveUseCase.getPersonList()
        log.debug("Request for all persons was successful")
        return personList.map { personToResponseMapper(it) }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPerson(
        @RequestBody personRequest: PersonRequest,
        httpRequest: HttpServletRequest,
        httpResponse: HttpServletResponse
    ) {
        val response = personCreateUseCase.create(personRequestToPersonTemplateMapper(personRequest))
        log.debug("Successfully created a new person with id $response")
        httpResponse.addHeader("Location", httpRequest.requestURI + "/" + response)
    }

    @PatchMapping("/{personId}")
    fun updatePerson(
        @PathVariable personId: Int,
        @RequestBody personRequest: PersonRequest
    ): PersonResponse {
        try {
            val personRequestWithId = personRequestToPersonMapper(personRequest, personId)
            val updatedPerson = personUpdateUseCase.update(personRequestWithId)
            log.debug("Updated person to {}", updatedPerson)
            return personToResponseMapper(updatedPerson)
        } catch (e: PersonNotExistException) {
            throw PersonNotFoundException(e.message)
        }
    }

    @DeleteMapping("/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePerson(
        @PathVariable personId: Int
    ) {
        personDeleteUseCase.delete(personId)
        log.debug("Deleted person with id {}", personId)
    }
}
