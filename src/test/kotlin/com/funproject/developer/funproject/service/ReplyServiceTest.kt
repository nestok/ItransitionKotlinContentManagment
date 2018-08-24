package com.funproject.developer.funproject.service

import com.funproject.developer.funproject.client.UserManagementClient
import com.funproject.developer.funproject.dto.replyDto.ReplyAddDto
import com.funproject.developer.funproject.dto.transformer.ContributorReplyTransformer
import com.funproject.developer.funproject.dto.transformer.ReplyAddTransformer
import com.funproject.developer.funproject.model.User
import com.funproject.developer.funproject.repository.StatusReplyRepository
import com.funproject.developer.funproject.repository.UserRepository
import com.funproject.developer.funproject.repository.dtoDao.ContributorDtoAccess
import com.funproject.developer.funproject.security.service.AuthenticationHelper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.beans.factory.annotation.Autowired


@RunWith(SpringRunner::class)
@SpringBootTest
class ReplyServiceTest @Autowired constructor(

) {
    @InjectMocks
    lateinit var replyService: ReplyService

    @Mock
    lateinit var statusReplyRepository: StatusReplyRepository

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var replyAddTransformer: ReplyAddTransformer

    @Mock
    lateinit var userManagementClient: UserManagementClient

    @Mock
    lateinit var contributorReplyTransformer: ContributorReplyTransformer

    @Mock
    lateinit var contributorDtoAccess: ContributorDtoAccess

    @Mock
    lateinit var authenticationHelper: AuthenticationHelper

    lateinit var user: User

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        user = userRepository.findById(1).orElse(User())

    }

    private final val ID : Long = 1

    @Test
    fun addReplyAndFindItTest() {
        Mockito.`when`(authenticationHelper.getCurrentUser()).thenReturn(user)
        val replyAddDto = ReplyAddDto(comment = "asdasd", location_id = 1, mood_id = 1, contributor_id = 2)
        replyService.addReply(replyAddDto)
        Mockito.verify(statusReplyRepository).save(replyAddTransformer.makeModel(replyAddDto))
    }

    @Test
    fun addMoodTest() {

    }



}
