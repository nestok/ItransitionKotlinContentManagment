package com.funproject.developer.funproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class PortalContentManagmentApplication

fun main(args: Array<String>) {
    runApplication<PortalContentManagmentApplication>(*args)
}

