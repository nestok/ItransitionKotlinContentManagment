package com.funproject.developer.funproject.controller

import com.funproject.developer.funproject.dto.replyDto.ReplyDto
import com.funproject.developer.funproject.dto.replyDto.ReplyAddDto
import com.funproject.developer.funproject.service.ReplyService
import org.springframework.beans.factory.annotation.Autowired
import com.funproject.developer.funproject.service.ReplyServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = arrayOf("http://localhost:4200"), maxAge = 3600)
@RequestMapping(value = "/reply")
class StatusReplyController @Autowired constructor(
        private var template: SimpMessagingTemplate,
        private val replyService: ReplyService
) {

    @Autowired
    fun StatusReplyController(template: SimpMessagingTemplate) {
        this.template = template
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/")
    fun findAllReplies(): ArrayList<ReplyDto> {
        return replyService.findAllReplies()
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/lastStatuses")
    fun findTeamStatuses(): ArrayList<ReplyDto> {
        return replyService.findTeamStatuses()
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addReply(@RequestBody reply: ReplyAddDto) {
        replyService.addReply(reply)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    fun deleteReply(@PathVariable(value = "id") id: Long) {
        replyService.deleteReply(id)
    }

    @MessageMapping("/send")
    fun onRecievedReply() {
        this.template.convertAndSend("/reply", "OK")
    }

}