package com.be.havesomefun

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class HaveSomeFunApplication

fun main(args: Array<String>) {
    runApplication<HaveSomeFunApplication>(*args)
}
