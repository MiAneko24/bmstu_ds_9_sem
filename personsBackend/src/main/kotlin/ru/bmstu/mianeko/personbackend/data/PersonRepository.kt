package ru.bmstu.mianeko.personbackend.data

import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.any
import org.ktorm.entity.find
import org.ktorm.entity.toList
import org.springframework.stereotype.Repository
import ru.bmstu.mianeko.personbackend.data.mappers.PersonEntityToDomainMapper
import ru.bmstu.mianeko.personbackend.data.models.PersonEntity
import ru.bmstu.mianeko.personbackend.data.models.persons
import ru.bmstu.mianeko.personbackend.domain.exceptions.PersonNotExistException
import ru.bmstu.mianeko.personbackend.domain.models.Person
import ru.bmstu.mianeko.personbackend.domain.repository.*
import java.util.Random

interface PersonRepository:
    PersonCreateRepository,
    PersonRetrieveRepository,
    PersonUpdateRepository,
    PersonDeleteRepository

@Repository
class PersonRepositoryImpl(
    val db: Database,
    val personEntityToDomainMapper: PersonEntityToDomainMapper,
): PersonRepository {
    override fun generateId(): Int {
        var newId: Int
        var retryCount: Int = 0
        do {
            newId = Random().nextInt()
            retryCount++
        } while (idExists(newId) && retryCount < MAX_GENERATE_ID_ATTEMPTS_AMOUNT)

        if (retryCount == MAX_GENERATE_ID_ATTEMPTS_AMOUNT) {
            throw IllegalStateException("Could not generate identifier")
        }

        return newId
    }

    override fun idExists(id: Int): Boolean {
        return db.persons.any { it.id eq id }
    }

    override fun getPerson(id: Int): Person {
        val personDb = db.persons.find { it.id eq id } ?: throw PersonNotExistException(id)
        return personEntityToDomainMapper(personDb)
    }

    override fun getPersonList(): List<Person> {
        return db.persons.toList().map(personEntityToDomainMapper)
    }

    override fun savePerson(person: Person) {
        val personEntity = PersonEntity {
            id = person.id
            name = person.name
            age = person.age
            address = person.address
            work = person.work
        }
        db.persons.add(personEntity)
    }

    override fun updatePerson(person: Person): Person {
        val personEntity = db.persons.find { it.id eq person.id } ?: throw PersonNotExistException(person.id)
        personEntity.apply {
            name = person.name
            age = person.age ?: age
            address = person.address ?: address
            work = person.work ?: work
            flushChanges()
        }
        return getPerson(person.id)
    }

    override fun deletePerson(id: Int) {
        db.persons
            .find { it.id eq id }
            ?.delete()
    }

    companion object {
        private const val MAX_GENERATE_ID_ATTEMPTS_AMOUNT = 10
    }
}
