package com.funproject.developer.funproject.controller

import com.funproject.developer.funproject.model.Mood
import com.funproject.developer.funproject.service.MoodService
import com.funproject.developer.funproject.service.MoodServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = arrayOf("http://localhost:4200"), maxAge = 3600)
@RequestMapping(value = "/mood")
class MoodController @Autowired constructor(
        private val moodService: MoodService
) {

    @GetMapping("/")
    fun findAllMoods(): ArrayList<Mood> {
        return moodService.findAllMoods()
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun addMood(@RequestBody mood: Mood) {
        moodService.addMood(mood)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun editMood(@RequestBody mood: Mood) {
        moodService.editMood(mood)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    fun deleteMood(@PathVariable(value = "id") id: Long) {
        moodService.deleteMood(id)
    }

}