package ru.bmstu.mianeko.personbackend.domain.exceptions

import ru.bmstu.mianeko.personbackend.domain.models.PersonError

open class PersonException(
    override val message: String?
): IllegalStateException(message)

data class PersonNotExistException(
    val id: Int,
    override val message: String = "Person with id $id does not exist"
): PersonException(message)

class PersonArgumentsException(
    override val message: String = "Invalid values detected in person request",
    val errors: Map<String, String>,
): PersonException(message) {
    val personError = PersonError(message, errors)
}
