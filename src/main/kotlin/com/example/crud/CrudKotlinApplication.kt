package com.example.crud

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class CrudKotlinApplication

fun main(args: Array<String>) {
    SpringApplication.run(CrudKotlinApplication::class.java, *args)
}
