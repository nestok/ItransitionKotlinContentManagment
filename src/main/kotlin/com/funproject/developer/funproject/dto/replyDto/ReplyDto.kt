package com.funproject.developer.funproject.dto.replyDto

import java.time.LocalDateTime

class ReplyDto(
        val id: Long = -1,
        val comment: String = "",
        val publish_date: LocalDateTime = LocalDateTime.now(),
        val location_name: String = "",
        val mood_text: String = "",
        val mood_icon: String = "",
        val contributor_firstname: String = "",
        val contributor_lastname: String = "")