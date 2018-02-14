package no.fint.labs

import mu.KotlinLogging
import no.fint.model.felles.Person
import org.springframework.core.ParameterizedTypeReference
import org.springframework.hateoas.Resources
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

private val logger = KotlinLogging.logger { }

inline fun <reified T : Any> typeRef(): ParameterizedTypeReference<T> = object : ParameterizedTypeReference<T>() {}

@RestController
class PersonController(val restTemplate: RestTemplate) {

    @GetMapping("/person")
    fun person(): ResponseEntity<Resources<Person>> {
        logger.info { "GET person" }

        val headers = HttpHeaders()
        headers.set("x-org-id", "pwf.no")
        headers.set("x-client", "kotlin-test-client")

        val result: ResponseEntity<Resources<Person>>? = restTemplate.exchange(
                "https://play-with-fint.felleskomponent.no/administrasjon/personal/person",
                HttpMethod.GET,
                HttpEntity("parameters", headers),
                typeRef<Resources<Person>>())

        result?.body?.let {
            logger.info { "Resources found: ${it.content.size}" }
            return ResponseEntity.ok(it)
        }

        return ResponseEntity.notFound().build()
    }

}