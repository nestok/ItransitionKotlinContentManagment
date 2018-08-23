package com.funproject.developer.funproject

import com.funproject.developer.funproject.controller.StatusReplyController
import com.funproject.developer.funproject.service.ReplyService
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class FunprojectApplicationTests {

//	@InjectMocks
//	lateinit var replyService: ReplyService
//
//	@InjectMocks
//	lateinit var statusReplyController: StatusReplyController

	@Test
	fun contextLoads() {
	}



//	@Test
//	fun testStatusReplyController() {
//		val result = statusReplyController.findAllReplies()
//		assertNotNull(result)
//		assertEquals("Hello service!", result)
//	}

}
