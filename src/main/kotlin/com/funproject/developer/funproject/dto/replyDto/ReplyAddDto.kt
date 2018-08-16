package com.funproject.developer.funproject.dto.replyDto

import java.time.LocalDateTime

class ReplyAddDto(
        val comment: String = "",
        val publish_date: LocalDateTime = LocalDateTime.now(),
        val location_id: Long = -1,
        val contributor_id: Long = -1,
        val mood_id: Long = -1)