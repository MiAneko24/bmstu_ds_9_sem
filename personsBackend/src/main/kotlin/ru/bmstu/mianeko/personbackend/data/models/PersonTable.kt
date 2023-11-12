package ru.bmstu.mianeko.personbackend.data.models

import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object PersonTable: Table<PersonEntity>("persons", schema = "persons") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val age = int("age").bindTo { it.age }
    val address = varchar("address").bindTo { it.address }
    val work = varchar("work").bindTo { it.work }
}

val Database.persons get() = this.sequenceOf(PersonTable)
