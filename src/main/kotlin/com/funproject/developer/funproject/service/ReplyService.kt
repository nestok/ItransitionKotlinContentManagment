package com.funproject.developer.funproject.service

import com.funproject.developer.funproject.client.UserManagementClient
import com.funproject.developer.funproject.dto.replyDto.ReplyDto
import com.funproject.developer.funproject.dto.replyDto.ReplyAddDto
import com.funproject.developer.funproject.dto.transformer.ContributorReplyTransformer
import com.funproject.developer.funproject.dto.transformer.ReplyAddTransformer
import com.funproject.developer.funproject.dto.userDto.ContributorDto
import com.funproject.developer.funproject.model.*
import com.funproject.developer.funproject.model.exception.EntityNotFoundException
import com.funproject.developer.funproject.model.exception.UserNotFoundException
import com.funproject.developer.funproject.repository.StatusReplyRepository
import com.funproject.developer.funproject.repository.dtoDao.ContributorDtoAccess
import com.funproject.developer.funproject.security.service.AuthenticationHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.UnsupportedOperationException


@Service
@Transactional
class ReplyService @Autowired constructor(
        private val statusReplyRepository: StatusReplyRepository,
        private val replyAddTransformer: ReplyAddTransformer,
        private val authenticationHelper: AuthenticationHelper,
        private val userManagementClient: UserManagementClient,
        private val contributorReplyTransformer: ContributorReplyTransformer,
        private val contributorDtoAccess: ContributorDtoAccess
) {

    fun findAllReplies(): ArrayList<ReplyDto> {
        val currentUser = authenticationHelper.getCurrentUser() ?: throw UserNotFoundException("User not found")
        if (currentUser.role == UserRole.ROLE_USER)
            return contributorDtoAccess.findAllNotPersonalCriteria(currentUser.id)
        return contributorReplyTransformer.makeDto(statusReplyRepository.findAll())
    }

    fun findTeamStatuses(): ArrayList<ReplyDto> {
        return contributorDtoAccess.findLastContributorReply()
    }

    fun findContributorsWithoutReply(): ArrayList<ContributorDto> {
        val contributors = userManagementClient.findAllContributors()
        return contributorDtoAccess.findContributorWithoutReply()
    }

    fun addReply(replyAddDto: ReplyAddDto) {
        val currentUser = authenticationHelper.getCurrentUser() ?: throw UserNotFoundException("User not found")
        if (currentUser.id == replyAddDto.contributor_id)
            throw UnsupportedOperationException("User can't write replies on himself")
        val reply: StatusReply = replyAddTransformer.makeModel(replyAddDto)
        statusReplyRepository.save(reply)
    }

    fun deleteReply(id: Long) {
        val deletedReply: StatusReply = statusReplyRepository.findById(id).orElse(null) ?: throw EntityNotFoundException("Reply not found")
        statusReplyRepository.delete(deletedReply)
    }

}