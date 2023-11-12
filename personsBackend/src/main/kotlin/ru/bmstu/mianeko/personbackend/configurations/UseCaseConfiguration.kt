package ru.bmstu.mianeko.personbackend.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.bmstu.mianeko.personbackend.domain.PersonTemplateVerifier
import ru.bmstu.mianeko.personbackend.domain.repository.PersonCreateRepository
import ru.bmstu.mianeko.personbackend.domain.repository.PersonDeleteRepository
import ru.bmstu.mianeko.personbackend.domain.repository.PersonRetrieveRepository
import ru.bmstu.mianeko.personbackend.domain.repository.PersonUpdateRepository
import ru.bmstu.mianeko.personbackend.domain.usecases.*

@Configuration
class UseCaseConfiguration {
    @Bean
    fun providePersonRetrieveUseCase(personRetrieveRepository: PersonRetrieveRepository): PersonRetrieveUseCase {
        return PersonRetrieveUseCaseImpl(personRetrieveRepository)
    }

    @Bean
    fun providePersonCreateUseCase(
        personCreateRepository: PersonCreateRepository,
        personTemplateVerifier: PersonTemplateVerifier
    ): PersonCreateUseCase {
        return PersonCreateUseCaseImpl(personCreateRepository, personTemplateVerifier)
    }

    @Bean
    fun providePersonUpdateUseCase(
        personUpdateRepository: PersonUpdateRepository,
        personTemplateVerifier: PersonTemplateVerifier
    ): PersonUpdateUseCase {
        return PersonUpdateUseCaseImpl(personUpdateRepository, personTemplateVerifier)
    }

    @Bean
    fun providePersonDeleteUseCase(personDeleteRepository: PersonDeleteRepository): PersonDeleteUseCase {
        return PersonDeleteUseCaseImpl(personDeleteRepository)
    }
}
