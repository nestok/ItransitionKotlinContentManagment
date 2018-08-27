package com.funproject.developer.funproject.controller

import com.funproject.developer.funproject.dto.userDto.ContributorDto
import com.funproject.developer.funproject.service.ReplyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = arrayOf("http://localhost:4200"), maxAge = 3600)
@RequestMapping(value = "/contributors")
class ContributorsController @Autowired constructor(
        private val replyService: ReplyService
) {

    @GetMapping("/withoutReply")
    fun findContributorsWithoutReply(): ArrayList<ContributorDto> {
        return replyService.findContributorsWithoutReply()
    }

}