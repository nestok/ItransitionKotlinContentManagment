package com.funproject.developer.funproject.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "status_reply")
data class StatusReply(

        val comment: String = "",

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

}