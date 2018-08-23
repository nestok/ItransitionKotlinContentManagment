package com.funproject.developer.funproject.dto.replyDto

import com.funproject.developer.funproject.dto.moodDto.MoodDto
import com.funproject.developer.funproject.dto.userDto.ContributorDto
import java.time.LocalDateTime

class ContributorReplyDto(
        val id: Long = -1,
        val comment: String = "",
        val publish_date: LocalDateTime = LocalDateTime.now(),
        val location_name: String = "",
        val mood_text: String = "",
        val mood_icon: String = "",
        val contributor_firstname: String = "",
        val contributor_lastname: String = ""
//        val reply: ReplyDto? = ReplyDto(),
//        val contributor: ContributorDto = ContributorDto()
)