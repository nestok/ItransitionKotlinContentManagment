package com.funproject.developer.funproject.controller

import com.funproject.developer.funproject.dto.replyDto.ReplyDto
import com.funproject.developer.funproject.service.ReplyService
import com.funproject.developer.funproject.service.ReplyServiceImpl
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.context.support.WithMockUser
import kotlin.collections.ArrayList


@RunWith(SpringRunner::class)
@SpringBootTest
class StatusReplyControllerTest {

    @Autowired
    lateinit var statusReplyController: StatusReplyController

    @MockBean
    lateinit var replyService: ReplyService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    @WithMockUser(roles = ["ADMIN", "USER"])
    fun findAllRepliesTest() {
        val replyList = ArrayList<ReplyDto>()
        replyList.add(ReplyDto())
        Mockito.`when`(replyService.findAllReplies()).thenReturn(replyList)
        assertEquals(statusReplyController.findAllReplies().size, replyList.size)
    }

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun addReplyTest() {
        Mockito.doNothing().`when`(replyService).deleteReply(anyLong())
        statusReplyController.deleteReply(1)
    }

}
