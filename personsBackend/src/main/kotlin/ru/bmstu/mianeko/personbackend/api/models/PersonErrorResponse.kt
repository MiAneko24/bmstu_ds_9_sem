package ru.bmstu.mianeko.personbackend.api.models

import com.fasterxml.jackson.annotation.JsonProperty

data class PersonErrorResponse(
    @JsonProperty
    val message: String,

    @JsonProperty
    val errors: Map<String, String>
)
