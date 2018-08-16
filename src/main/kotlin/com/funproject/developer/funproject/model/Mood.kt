package com.funproject.developer.funproject.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "mood")
data class Mood(

        val icon: String = "",

        val text: String = "",

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1) {

}