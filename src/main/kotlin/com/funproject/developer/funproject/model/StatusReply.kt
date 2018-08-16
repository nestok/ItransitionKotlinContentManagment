package com.funproject.developer.funproject.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.*

@Entity
@Table(name = "status_reply")
data class StatusReply(

        val comment: String = "",

//        @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
        val publish_date: LocalDateTime = LocalDateTime.now(),

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "location_id")
        var location: Location = Location(),

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "mood_id")
        var mood: Mood = Mood(),

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        var user: User = User(),

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1) {

        //.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))

//    override fun toString(): String {
//        return "{location: ${this.location}, mood: ${this.mood}, comment: ${this.comment}," +
//                " publish_date: ${this.publish_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}}"
//    }

}