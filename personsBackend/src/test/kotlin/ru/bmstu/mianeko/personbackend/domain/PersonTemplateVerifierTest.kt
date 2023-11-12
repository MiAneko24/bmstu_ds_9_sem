package ru.bmstu.mianeko.personbackend.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import ru.bmstu.mianeko.personbackend.domain.exceptions.PersonArgumentsException

import ru.bmstu.mianeko.personbackend.domain.models.PersonTemplate

class PersonTemplateVerifierTest {

    private val personTemplateVerifier = PersonTemplateVerifierImpl()

    @Test
    fun verifyCorrectTemplateWithOnlyName() {
        val personTemplate = PersonTemplate(
            name = "Алена"
        )

        assertDoesNotThrow {
            personTemplateVerifier.verify(personTemplate)
        }
    }

//    @Test
//    fun verifyTemplateWithNumbersInName() {
//        val personTemplate = PersonTemplate(
//            name = "Алена632"
//        )
//        val expectedException = PersonArgumentsException(
//            errors = mapOf(PersonTemplateVerifierImpl.NAME_FIELD to PersonTemplateVerifierImpl.NAME_ALLOWED_SYMBOLS_ERROR)
//        )
//
//        val exception = assertThrows<PersonArgumentsException> {
//            personTemplateVerifier.verify(personTemplate)
//        }
//
//        assertEquals(expectedException.personError, exception.personError)
//    }

    @Test
    fun verifyTemplateWithInvalidNameLength() {
        val personTemplate = PersonTemplate(
            name = "f"
        )
        val expectedException = PersonArgumentsException(
            errors = mapOf(PersonTemplateVerifierImpl.NAME_FIELD to PersonTemplateVerifierImpl.NAME_LENGTH_ERROR)
        )

        val exception = assertThrows<PersonArgumentsException> {
            personTemplateVerifier.verify(personTemplate)
        }

        assertEquals(expectedException.personError, exception.personError)
    }

    @Test
    fun verifyCorrectTemplateWithNameAndAge() {
        val personTemplate = PersonTemplate(
            name = "Алена",
            age = 32
        )

        assertDoesNotThrow {
            personTemplateVerifier.verify(personTemplate)
        }
    }

    @Test
    fun verifyTemplateWithInvalidAge() {
        val personTemplate = PersonTemplate(
            name = "Алена",
            age = 1234
        )
        val expectedException = PersonArgumentsException(
            errors = mapOf(PersonTemplateVerifierImpl.AGE_FIELD to PersonTemplateVerifierImpl.AGE_ERROR)
        )

        val exception = assertThrows<PersonArgumentsException> {
            personTemplateVerifier.verify(personTemplate)
        }

        assertEquals(expectedException.personError, exception.personError)
    }

    @Test
    fun verifyCorrectTemplateWithNameAndWork() {
        val personTemplate = PersonTemplate(
            name = "Алена",
            work = """Юрист в ООО "Права и люди""""
        )

        assertDoesNotThrow {
            personTemplateVerifier.verify(personTemplate)
        }
    }

    @Test
    fun verifyTemplateWithInvalidWorkLength() {
        val personTemplate = PersonTemplate(
            name = "Алена",
            work = "q"
        )
        val expectedException = PersonArgumentsException(
            errors = mapOf(PersonTemplateVerifierImpl.WORK_FIELD to PersonTemplateVerifierImpl.WORK_LENGTH_ERROR)
        )

        val exception = assertThrows<PersonArgumentsException> {
            personTemplateVerifier.verify(personTemplate)
        }

        assertEquals(expectedException.personError, exception.personError)
    }

    @Test
    fun verifyCorrectTemplateWithNameAndShortAddress() {
        val personTemplate = PersonTemplate(
            name = "Алена",
            address = "г. Йошкар-Ола, ул. 5-го Февраля, д. 152"
        )

        assertDoesNotThrow {
            personTemplateVerifier.verify(personTemplate)
        }
    }

    @Test
    fun verifyTemplateWithInvalidAddressLength() {
        val personTemplate = PersonTemplate(
            name = "Алена",
            address = "ffd"
        )
        val expectedException = PersonArgumentsException(
            errors = mapOf(PersonTemplateVerifierImpl.ADDRESS_FIELD to PersonTemplateVerifierImpl.ADDRESS_LENGTH_ERROR)
        )

        val exception = assertThrows<PersonArgumentsException> {
            personTemplateVerifier.verify(personTemplate)
        }

        assertEquals(expectedException.personError, exception.personError)
    }

//    @Test
//    fun verifyCorrectTemplateWithNameAndShortAddressWithBuildingNumber() {
//        val personTemplate = PersonTemplate(
//            name = "Алена",
//            address = "г. Йошкар-Ола, ул. 5-го Февраля, д. 152, к. 12А"
//        )
//
//        assertDoesNotThrow {
//            personTemplateVerifier.verify(personTemplate)
//        }
//    }
//
//    @Test
//    fun verifyCorrectTemplateWithNameAndFullAddress() {
//        val personTemplate = PersonTemplate(
//            name = "Алена",
//            address = "г. Йошкар-Ола, ул. 5-го Февраля, д. 152, к. 12А, кв. 44"
//        )
//
//        assertDoesNotThrow {
//            personTemplateVerifier.verify(personTemplate)
//        }
//    }
//
//    @Test
//    fun verifyTemplateWithInvalidAddressCitySymbols() {
//        val personTemplate = PersonTemplate(
//            name = "Алена",
//            address = "г. Йошкар-Ола??, ул. 5-го Февраля, д. 152, к. 12А, кв. 44"
//        )
//        val expectedException = PersonArgumentsException(
//            errors = mapOf(PersonTemplateVerifierImpl.ADDRESS_FIELD to PersonTemplateVerifierImpl.ADDRESS_ERROR)
//        )
//
//        val exception = assertThrows<PersonArgumentsException> {
//            personTemplateVerifier.verify(personTemplate)
//        }
//
//        assertEquals(expectedException.personError, exception.personError)
//    }
//
//    @Test
//    fun verifyTemplateWithInvalidAddressNoCity() {
//        val personTemplate = PersonTemplate(
//            name = "Алена",
//            address = "ул. 5-го Февраля, д. 152, к. 12А, кв. 44"
//        )
//        val expectedException = PersonArgumentsException(
//            errors = mapOf(PersonTemplateVerifierImpl.ADDRESS_FIELD to PersonTemplateVerifierImpl.ADDRESS_ERROR)
//        )
//
//        val exception = assertThrows<PersonArgumentsException> {
//            personTemplateVerifier.verify(personTemplate)
//        }
//
//        assertEquals(expectedException.personError, exception.personError)
//    }
//
//    @Test
//    fun verifyTemplateWithInvalidAddressStreetSymbols() {
//        val personTemplate = PersonTemplate(
//            name = "Алена",
//            address = "г. Йошкар-Ола, ул. 5-го Февра/ля, д. 152, к. 12А, кв. 44"
//        )
//        val expectedException = PersonArgumentsException(
//            errors = mapOf(PersonTemplateVerifierImpl.ADDRESS_FIELD to PersonTemplateVerifierImpl.ADDRESS_ERROR)
//        )
//
//        val exception = assertThrows<PersonArgumentsException> {
//            personTemplateVerifier.verify(personTemplate)
//        }
//
//        assertEquals(expectedException.personError, exception.personError)
//    }
//
//    @Test
//    fun verifyTemplateWithInvalidAddressNoStreet() {
//        val personTemplate = PersonTemplate(
//            name = "Алена",
//            address = "г. Йошкар-Ола, д. 152, к. 12А, кв. 44"
//        )
//        val expectedException = PersonArgumentsException(
//            errors = mapOf(PersonTemplateVerifierImpl.ADDRESS_FIELD to PersonTemplateVerifierImpl.ADDRESS_ERROR)
//        )
//
//        val exception = assertThrows<PersonArgumentsException> {
//            personTemplateVerifier.verify(personTemplate)
//        }
//
//        assertEquals(expectedException.personError, exception.personError)
//    }
//
//    @Test
//    fun verifyTemplateWithInvalidAddressHouseSymbols() {
//        val personTemplate = PersonTemplate(
//            name = "Алена",
//            address = "г. Йошкар-Ола, ул. 5-го Февраля, д. fgh, к. 12А, кв. 44"
//        )
//        val expectedException = PersonArgumentsException(
//            errors = mapOf(PersonTemplateVerifierImpl.ADDRESS_FIELD to PersonTemplateVerifierImpl.ADDRESS_ERROR)
//        )
//
//        val exception = assertThrows<PersonArgumentsException> {
//            personTemplateVerifier.verify(personTemplate)
//        }
//
//        assertEquals(expectedException.personError, exception.personError)
//    }
//
//    @Test
//    fun verifyTemplateWithInvalidAddressNoHouse() {
//        val personTemplate = PersonTemplate(
//            name = "Алена",
//            address = "г. Йошкар-Ола, ул. 5-го Февраля, к. 12А, кв. 44"
//        )
//        val expectedException = PersonArgumentsException(
//            errors = mapOf(PersonTemplateVerifierImpl.ADDRESS_FIELD to PersonTemplateVerifierImpl.ADDRESS_ERROR)
//        )
//
//        val exception = assertThrows<PersonArgumentsException> {
//            personTemplateVerifier.verify(personTemplate)
//        }
//
//        assertEquals(expectedException.personError, exception.personError)
//    }
//
//    @Test
//    fun verifyTemplateWithInvalidAddressBuildingSymbols() {
//        val personTemplate = PersonTemplate(
//            name = "Алена",
//            address = "г. Йошкар-Ола, ул. 5-го Февраля, д. fgh, к. 12-А, кв. 44"
//        )
//        val expectedException = PersonArgumentsException(
//            errors = mapOf(PersonTemplateVerifierImpl.ADDRESS_FIELD to PersonTemplateVerifierImpl.ADDRESS_ERROR)
//        )
//
//        val exception = assertThrows<PersonArgumentsException> {
//            personTemplateVerifier.verify(personTemplate)
//        }
//
//        assertEquals(expectedException.personError, exception.personError)
//    }
//
//    @Test
//    fun verifyTemplateWithInvalidAddressFlatSymbols() {
//        val personTemplate = PersonTemplate(
//            name = "Алена",
//            address = "г. Йошкар-Ола, ул. 5-го Февраля, д. fgh, к. 12-А, кв. seur"
//        )
//        val expectedException = PersonArgumentsException(
//            errors = mapOf(PersonTemplateVerifierImpl.ADDRESS_FIELD to PersonTemplateVerifierImpl.ADDRESS_ERROR)
//        )
//
//        val exception = assertThrows<PersonArgumentsException> {
//            personTemplateVerifier.verify(personTemplate)
//        }
//
//        assertEquals(expectedException.personError, exception.personError)
//    }
//
//    @Test
//    fun verifyTemplateWithInvalidAddressRandomString() {
//        val personTemplate = PersonTemplate(
//            name = "Алена",
//            address = "skjdfg7isyibskdf"
//        )
//        val expectedException = PersonArgumentsException(
//            errors = mapOf(PersonTemplateVerifierImpl.ADDRESS_FIELD to PersonTemplateVerifierImpl.ADDRESS_ERROR)
//        )
//
//        val exception = assertThrows<PersonArgumentsException> {
//            personTemplateVerifier.verify(personTemplate)
//        }
//
//        assertEquals(expectedException.personError, exception.personError)
//    }

    @Test
    fun verifyFullCorrectTemplate() {
        val personTemplate = PersonTemplate(
            name = "Алена",
            age = 30,
            work = """Юрист в ООО "Права и люди"""",
            address = "г. Йошкар-Ола, ул. 5-го Февраля, д. 152"
        )

        assertDoesNotThrow {
            personTemplateVerifier.verify(personTemplate)
        }
    }

    @Test
    fun verifyTemplateWithErrorInAllFields() {
        val personTemplate = PersonTemplate(
            name = "в",
            age = 12314,
            work = "a",
            address = "гfg"
        )

        val expectedException = PersonArgumentsException(
            errors = mapOf(
                PersonTemplateVerifierImpl.NAME_FIELD to PersonTemplateVerifierImpl.NAME_LENGTH_ERROR,
                PersonTemplateVerifierImpl.AGE_FIELD to PersonTemplateVerifierImpl.AGE_ERROR,
                PersonTemplateVerifierImpl.ADDRESS_FIELD to PersonTemplateVerifierImpl.ADDRESS_LENGTH_ERROR,
                PersonTemplateVerifierImpl.WORK_FIELD to PersonTemplateVerifierImpl.WORK_LENGTH_ERROR,
            )
        )

        val exception = assertThrows<PersonArgumentsException> {
            personTemplateVerifier.verify(personTemplate)
        }

        assertEquals(expectedException.personError, exception.personError)

    }
}
