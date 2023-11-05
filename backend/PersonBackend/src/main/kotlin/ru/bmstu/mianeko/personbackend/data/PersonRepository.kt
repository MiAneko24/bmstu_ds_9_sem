package ru.bmstu.mianeko.personbackend.data

import io.ebean.Database
import org.springframework.stereotype.Repository
import ru.bmstu.mianeko.personbackend.data.mappers.PersonDbToDomainMapper
import ru.bmstu.mianeko.personbackend.data.models.PersonDb
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
    val personDbToDomainMapper: PersonDbToDomainMapper,
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
        return db.find(PersonDb::class.java, id) != null
    }

    override fun getPerson(id: Int): Person {
        val personDb = db.find(PersonDb::class.java, id) ?: throw PersonNotExistException(id)
        return personDbToDomainMapper(personDb)
    }

    override fun getPersonList(): List<Person> {
        return db.find(PersonDb::class.java).findList().map(personDbToDomainMapper)
    }

    override fun savePerson(person: Person): Person {
        db.save(person)
        return getPerson(person.id)
    }

    override fun deletePerson(id: Int) {
        db.delete(PersonDb::class.java, id)
    }

    companion object {
        private const val MAX_GENERATE_ID_ATTEMPTS_AMOUNT = 10
    }
}
