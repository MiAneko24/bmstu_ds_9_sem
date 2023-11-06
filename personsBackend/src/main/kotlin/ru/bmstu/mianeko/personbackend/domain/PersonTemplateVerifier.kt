package ru.bmstu.mianeko.personbackend.domain

import ru.bmstu.mianeko.personbackend.domain.exceptions.PersonArgumentsException
import ru.bmstu.mianeko.personbackend.domain.models.PersonTemplate

interface PersonTemplateVerifier {
    fun <T: PersonTemplate> verify(personTemplate: T)
}

class PersonTemplateVerifierImpl: PersonTemplateVerifier {
    override fun <T : PersonTemplate> verify(personTemplate: T) {
        val errorMap: MutableMap<String, String> = mutableMapOf()
        getNameError(personTemplate.name)?.let { errorMessage ->
            errorMap.put(NAME_FIELD, errorMessage)
        }
        getAgeError(personTemplate.age)?.let { errorMessage ->
            errorMap.put(AGE_FIELD, errorMessage)
        }
        getAddressError(personTemplate.address)?.let { errorMessage ->
            errorMap.put(ADDRESS_FIELD, errorMessage)
        }
        getWorkError(personTemplate.work)?.let { errorMessage ->
            errorMap.put(WORK_FIELD, errorMessage)
        }
        if (errorMap.isNotEmpty()) {
            throw PersonArgumentsException(errors = errorMap)
        }
    }

    private fun getNameError(name: String): String? {
        return when {
            (name.length < MIN_NAME_LEN || name.length > MAX_NAME_LEN) -> NAME_LENGTH_ERROR
            !nameSymbolsCorrect(name) -> NAME_ALLOWED_SYMBOLS_ERROR
            else -> null
        }
    }

    private fun nameSymbolsCorrect(name: String): Boolean {
        return name.all { it.isLetter() || it in NAME_ALLOWED_SYMBOLS }
    }

    private fun getAgeError(age: Int?): String? {
        return if (age != null && (age < MIN_AGE || age > MAX_AGE)) AGE_ERROR else null
    }

    private fun getAddressError(address: String?): String? {
        return if (address != null && !(ADDRESS_REGEXP matches address)) ADDRESS_ERROR else null
    }

    private fun getWorkError(work: String?): String? {
        return if (work != null && (work.length < WORK_MIN_LENGTH || work.length > WORK_MAX_LENGTH)) WORK_LENGTH_ERROR else null
    }

    companion object {
        const val NAME_FIELD = "name"
        private const val MIN_NAME_LEN = 2
        private const val MAX_NAME_LEN = 20
        const val NAME_LENGTH_ERROR = "Person name should have more than $MIN_NAME_LEN " +
            "and not more than $MAX_NAME_LEN symbols"
        private val NAME_ALLOWED_SYMBOLS = listOf('-', ' ')
        val NAME_ALLOWED_SYMBOLS_ERROR = "Name can consist of letters or symbols $NAME_ALLOWED_SYMBOLS"

        const val AGE_FIELD = "age"
        private const val MAX_AGE = 120
        private const val MIN_AGE = 0
        const val AGE_ERROR = "Age should be a number between $MIN_AGE and $MAX_AGE"

        const val ADDRESS_FIELD = "address"
        private val ADDRESS_REGEXP = """^г\. [[а-яёА-ЯЁ]\w\-\s]+, ул\. [[а-яёА-ЯЁ]\w\-\s\d]+, д\. \d+(, к\. \d+[\w[а-яёА-ЯЁ]]?)?(, кв\. \d+)?$""".toRegex()
        const val ADDRESS_ERROR = "Address should be in format \"г. <City>, " +
            "ул. <Street>, д. <House number>(, к. <Building number>)(, к. <Flat number>)." +
            "Round brackets mean part is optional."

        const val WORK_FIELD = "work"
        private const val WORK_MIN_LENGTH = 2
        private const val WORK_MAX_LENGTH = 100
        const val WORK_LENGTH_ERROR = "Person work should have more than $MIN_NAME_LEN " +
            "and not more than $MAX_NAME_LEN symbols"
    }
}
