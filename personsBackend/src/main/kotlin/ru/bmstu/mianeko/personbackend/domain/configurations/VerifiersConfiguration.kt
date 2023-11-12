package ru.bmstu.mianeko.personbackend.domain.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.bmstu.mianeko.personbackend.domain.PersonTemplateVerifier
import ru.bmstu.mianeko.personbackend.domain.PersonTemplateVerifierImpl

@Configuration
class VerifiersConfiguration {
    @Bean
    fun providePersonTemplateVerifier(): PersonTemplateVerifier {
        return PersonTemplateVerifierImpl()
    }
}
