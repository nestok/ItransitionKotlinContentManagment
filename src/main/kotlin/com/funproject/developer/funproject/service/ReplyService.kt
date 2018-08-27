package com.funproject.developer.funproject.service

import com.funproject.developer.funproject.dto.replyDto.ReplyDto
import com.funproject.developer.funproject.dto.replyDto.ReplyAddDto
import com.funproject.developer.funproject.dto.userDto.ContributorDto


interface ReplyService  {

    fun findAllReplies(): ArrayList<ReplyDto>

    fun findTeamStatuses(): ArrayList<ReplyDto>

    fun findContributorsWithoutReply(): ArrayList<ContributorDto>

    fun addReply(replyAddDto: ReplyAddDto)

    fun deleteReply(id: Long)

}