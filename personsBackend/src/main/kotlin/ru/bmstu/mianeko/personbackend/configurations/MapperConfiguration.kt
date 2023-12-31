package ru.bmstu.mianeko.personbackend.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.bmstu.mianeko.personbackend.api.mappers.PersonErrorToPersonErrorResponseMapper
import ru.bmstu.mianeko.personbackend.api.mappers.PersonRequestToPersonMapper
import ru.bmstu.mianeko.personbackend.api.mappers.PersonRequestToPersonTemplateMapper
import ru.bmstu.mianeko.personbackend.api.mappers.PersonToPersonResponseMapper
import ru.bmstu.mianeko.personbackend.data.mappers.PersonEntityToDomainMapper

@Configuration
class MapperConfiguration {
    @Bean
    fun providePersonToPersonResponseMapper(): PersonToPersonResponseMapper {
        return PersonToPersonResponseMapper()
    }

    @Bean
    fun providePersonRequestToPersonTemplateMapper(): PersonRequestToPersonTemplateMapper {
        return PersonRequestToPersonTemplateMapper()
    }

    @Bean
    fun providePersonErrorToPersonErrorResponseMapper(): PersonErrorToPersonErrorResponseMapper {
        return PersonErrorToPersonErrorResponseMapper()
    }

    @Bean
    fun providePersonRequestToPersonMapper(): PersonRequestToPersonMapper {
        return PersonRequestToPersonMapper()
    }

    @Bean
    fun providePersonDbToDomainMapper(): PersonEntityToDomainMapper {
        return PersonEntityToDomainMapper()
    }
}
