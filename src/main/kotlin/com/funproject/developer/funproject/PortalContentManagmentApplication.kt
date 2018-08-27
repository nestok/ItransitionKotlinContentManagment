package com.funproject.developer.funproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.sql.DataSource


@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EntityScan("com.funproject.developer.funproject.model")
@EnableJpaRepositories("com.funproject.developer.funproject.repository", entityManagerFactoryRef="entityManagerFactory")
@PropertySource("classpath:application.properties")
class PortalContentManagmentApplication{

    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("org.postgresql.Driver")
        dataSource.url = "jdbc:postgresql://localhost/funnydb"
        dataSource.username = "postgres"
        dataSource.password = "postgres"
        return dataSource
    }

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource()
        em.setPackagesToScan("com.funproject.developer.funproject.model")
        val vendorAdapter = HibernateJpaVendorAdapter()
        em.jpaVendorAdapter = vendorAdapter
        return em
    }
}

fun main(args: Array<String>) {
    runApplication<PortalContentManagmentApplication>(*args)
}

