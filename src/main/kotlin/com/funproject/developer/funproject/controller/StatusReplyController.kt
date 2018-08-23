package com.funproject.developer.funproject.controller

import com.funproject.developer.funproject.dto.replyDto.ContributorReplyDto
import com.funproject.developer.funproject.dto.replyDto.ReplyAddDto
import com.funproject.developer.funproject.dto.userDto.ContributorDto
import com.funproject.developer.funproject.model.Location
import com.funproject.developer.funproject.model.Mood
import com.funproject.developer.funproject.model.StatusReply
import org.springframework.beans.factory.annotation.Autowired
import com.funproject.developer.funproject.service.ReplyService
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
        private var replyService: ReplyService
) {

    @Autowired
    fun StatusReplyController(template: SimpMessagingTemplate) {
        this.template = template
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/getAll")
    fun findAllReplies(): ArrayList<ContributorReplyDto> {
        return replyService.findAllReplies()
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/getTeamStatuses")
    fun findTeamStatuses(): ArrayList<ContributorReplyDto> {
        return replyService.findTeamStatuses()
    }

    @GetMapping("/getMoods")
    fun findAllMoods(): ArrayList<Mood> {
        return replyService.findAllMoods()
    }

    @GetMapping("/getContributorsWithoutReply")
    fun findContributorsWithoutReply(): ArrayList<ContributorDto> {
        return replyService.findContributorsWithoutReply()
    }

    @GetMapping("/getLocations")
    fun findAllLocations(): ArrayList<Location> {
        return replyService.findAllLocations()
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteMood/{id}")
    fun deleteUser(@PathVariable(value = "id") id: Long) {
        replyService.deleteMood(id)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addMood")
    @ResponseStatus(HttpStatus.OK)
    fun addMood(@RequestBody mood: Mood) {
        replyService.addMood(mood)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editMood")
    @ResponseStatus(HttpStatus.OK)
    fun editMood(@RequestBody mood: Mood) {
        replyService.editMood(mood)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addLocation")
    @ResponseStatus(HttpStatus.OK)
    fun addLocation(@RequestBody location: Location) {
        replyService.addLocation(location)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostMapping("/addReply")
    @ResponseStatus(HttpStatus.OK)
    fun addReply(@RequestBody reply: ReplyAddDto) {
        replyService.addReply(reply)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editLocation")
    @ResponseStatus(HttpStatus.OK)
    fun editLocation(@RequestBody location: Location) {
        replyService.editLocation(location)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteLocation/{id}")
    fun deleteLocation(@PathVariable(value = "id") id: Long) {
        replyService.deleteLocation(id)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteReply/{id}")
    fun deleteReply(@PathVariable(value = "id") id: Long) {
        replyService.deleteReply(id)
    }

    @MessageMapping("/send/reply")
    fun onRecievedReply() {
        this.template.convertAndSend("/reply", "OK")
    }

}