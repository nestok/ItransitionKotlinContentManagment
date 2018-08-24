package com.funproject.developer.funproject.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "location")
data class Location(

        val name: String = "",

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1)