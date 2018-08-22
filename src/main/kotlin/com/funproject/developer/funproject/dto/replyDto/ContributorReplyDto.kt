package com.funproject.developer.funproject.dto.replyDto

import com.funproject.developer.funproject.dto.moodDto.MoodDto
import com.funproject.developer.funproject.dto.userDto.ContributorDto
import java.time.LocalDateTime

class ContributorReplyDto(
        val reply: ReplyDto? = ReplyDto(),
        val contributor: ContributorDto = ContributorDto())