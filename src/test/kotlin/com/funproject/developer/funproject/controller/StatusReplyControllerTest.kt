package com.funproject.developer.funproject.controller

import com.funproject.developer.funproject.dto.replyDto.ReplyAddDto
import com.funproject.developer.funproject.model.StatusReply
import com.funproject.developer.funproject.model.User
import com.funproject.developer.funproject.service.ReplyService
import junit.framework.Assert.assertNotNull
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
import org.springframework.messaging.simp.SimpMessagingTemplate
import java.util.*
import kotlin.collections.ArrayList


@RunWith(SpringRunner::class)
@SpringBootTest
class StatusReplyControllerTest @Autowired constructor(

) {
    @InjectMocks
    lateinit var statusReplyController: StatusReplyController

    @Mock
    lateinit var template: SimpMessagingTemplate

    @Mock
    lateinit var replyService: ReplyService

    @Mock
    lateinit var reply: StatusReply

    lateinit var user: User

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        statusReplyController = StatusReplyController(template, replyService)
    }

    private final val ID : Long = 1

    @Test
    fun findAllRepliesTest() {
//        val replyList = ArrayList<StatusReply>()
//        replyList.add()
//        Mockito.`when`(authenticationHelper.getCurrentUser()).thenReturn(user)
//        val replyAddDto = ReplyAddDto(comment = "asdasd", location_id = 1, mood_id = 1, contributor_id = 2)
//        replyService.addReply(replyAddDto)
//        Mockito.verify(statusReplyRepository).save(replyAddTransformer.makeModel(replyAddDto))
    }

}
