package com.funproject.developer.funproject.dto.replyDto

import com.funproject.developer.funproject.dto.moodDto.MoodDto
import java.time.LocalDateTime

class ReplyDto(
        val id: Long = -1,
        val comment: String = "",
        val publish_date: LocalDateTime = LocalDateTime.now(),
        val location_name: String = "",
        val mood: MoodDto = MoodDto()){
}