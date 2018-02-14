package no.fint.labs

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.hateoas.config.EnableHypermediaSupport

@EnableHypermediaSupport(type = [(EnableHypermediaSupport.HypermediaType.HAL)])
@SpringBootApplication
class ClientExampleKotlinSpringApplication

fun main(args: Array<String>) {
    SpringApplication.run(ClientExampleKotlinSpringApplication::class.java, *args)
}
