package ru.bmstu.mianeko.personbackend.data.models

import org.ktorm.entity.Entity

interface PersonEntity : Entity<PersonEntity> {
    companion object : Entity.Factory<PersonEntity>()
    var id: Int
    var name: String
    var age: Int?
    var address: String?
    var work: String?
}
