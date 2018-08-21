package com.funproject.developer.funproject.dto.transformer

import com.funproject.developer.funproject.dto.replyDto.ReplyAddDto
import com.funproject.developer.funproject.dto.userDto.ContributorDto
import com.funproject.developer.funproject.model.StatusReply
import com.funproject.developer.funproject.model.exception.EntityNotFoundException
import com.funproject.developer.funproject.model.exception.UserNotFoundException
import com.funproject.developer.funproject.repository.LocationRepository
import com.funproject.developer.funproject.repository.MoodRepository
import com.funproject.developer.funproject.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class ContributorReplyTransformer(
        private val userRepository: UserRepository,
        private val moodRepository: MoodRepository,
        private val locationRepository: LocationRepository
) {

//    fun makeLatestContributorReplyDto(contributor: ContributorDto): StatusReply {
//        val user = userRepository.findById(replyAddDto.contributor_id).orElse(null)
//                ?: throw UserNotFoundException("User not found")
//        val mood = moodRepository.findById(replyAddDto.mood_id).orElse(null)
//                ?: throw EntityNotFoundException("Mood not found")
//        val location = locationRepository.findById(replyAddDto.location_id).orElse(null)
//                ?: throw EntityNotFoundException("Location not found")
//        return StatusReply(user = user, mood = mood, location = location, comment = replyAddDto.comment)
//    }
}
