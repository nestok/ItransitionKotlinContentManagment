package com.funproject.developer.funproject.service

import com.funproject.developer.funproject.dto.replyDto.ReplyAddDto
import com.funproject.developer.funproject.dto.transformer.ReplyAddTransformer
import com.funproject.developer.funproject.model.StatusReply
import com.funproject.developer.funproject.model.User
import com.funproject.developer.funproject.repository.StatusReplyRepository
import com.funproject.developer.funproject.security.service.AuthenticationHelper
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest
class ReplyServiceImplTest {

    @Autowired
    private lateinit var replyService: ReplyService

    @MockBean
    private lateinit var statusReplyRepository: StatusReplyRepository

    @MockBean
    private lateinit var replyAddTransformer: ReplyAddTransformer

    @MockBean
    private lateinit var authenticationHelper: AuthenticationHelper

    private lateinit var reply: StatusReply

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        reply = statusReplyRepository.findById(ID).orElse(StatusReply())
    }

    private final val ID : Long = 1

    @Test
    fun addReplyAndFindItTest() {
        Mockito.`when`(authenticationHelper.getCurrentUser()).thenReturn(User())
        val replyAddDto = ReplyAddDto(comment = "asdasd", location_id = 1, mood_id = 1, contributor_id = 2)
        replyService.addReply(replyAddDto)
        Mockito.verify(statusReplyRepository).save(replyAddTransformer.makeModel(replyAddDto))
    }

    @Test
    fun findAllRepliesTest() {
        Mockito.`when`(authenticationHelper.getCurrentUser()).thenReturn(User())
        val result = replyService.findAllReplies()
        assertNotNull(result)
    }

    @Test
    fun deleteReplyTest() {
        Mockito.`when`(statusReplyRepository.findById(ID)).thenReturn(Optional.ofNullable(reply))
        replyService.deleteReply(ID)
        Mockito.verify(statusReplyRepository).delete(reply)
    }

}
