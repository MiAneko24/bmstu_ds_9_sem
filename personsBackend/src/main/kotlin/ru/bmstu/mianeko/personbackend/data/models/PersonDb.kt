package ru.bmstu.mianeko.personbackend.data.models

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "person", schema = "public")
data class PersonDb(
    @Id
    val id: Int,
    val name: String,
    val age: Int?,
    val address: String?,
    val work: String?
)
