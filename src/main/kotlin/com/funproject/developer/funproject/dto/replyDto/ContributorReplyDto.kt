package com.funproject.developer.funproject.dto.replyDto

import com.funproject.developer.funproject.dto.moodDto.MoodDto
import com.funproject.developer.funproject.dto.userDto.ContributorDto
import java.time.LocalDateTime

class ContributorReplyDto(
        val comment: String = "",
        val publish_date: LocalDateTime = LocalDateTime.now(),
        val location_name: String = "",
        val contributorDto: ContributorDto = ContributorDto(),
        val moodDto: MoodDto = MoodDto())