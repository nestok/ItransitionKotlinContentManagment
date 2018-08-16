package com.funproject.developer.funproject.service

import com.funproject.developer.funproject.dto.replyDto.ReplyAddDto
import com.funproject.developer.funproject.dto.transformer.ReplyAddTransformer
import com.funproject.developer.funproject.model.*
import com.funproject.developer.funproject.model.exception.EntityNotFoundException
import com.funproject.developer.funproject.model.exception.UserNotFoundException
import com.funproject.developer.funproject.repository.LocationRepository
import com.funproject.developer.funproject.repository.MoodRepository
import com.funproject.developer.funproject.repository.StatusReplyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ReplyService @Autowired constructor(
        private val statusReplyRepository: StatusReplyRepository,
        private val moodRepository: MoodRepository,
        private val locationRepository: LocationRepository,
        private val replyAddTransformer: ReplyAddTransformer
) {

    fun findAllReplies(): ArrayList<StatusReply> {
        return statusReplyRepository!!.findAll()
    }

    fun findAllMoods(): ArrayList<Mood> {
        return moodRepository!!.findAll()
    }

    fun findAllLocations(): ArrayList<Location> {
        return locationRepository!!.findAll()
    }

    fun addReply(replyAddDto: ReplyAddDto) {
        val reply: StatusReply = replyAddTransformer.makeModel(replyAddDto)
        statusReplyRepository.save(reply)
    }

    fun deleteMood(id: Long) {
        val deletedMood: Mood = moodRepository.findById(id).orElse(null)
                ?: throw EntityNotFoundException("Mood not found")
        moodRepository.delete(deletedMood)
    }

    fun addMood(mood: Mood) {
        moodRepository.save(mood)
    }

    fun editMood(mood: Mood) {
        moodRepository.save(mood)
    }

    fun addLocation(location: Location) {
        locationRepository.save(location)
    }

    fun editLocation(location: Location) {
        locationRepository.save(location)
    }

    fun deleteLocation(id: Long) {
        val deletedLocation: Location = locationRepository.findById(id).orElse(null)
                ?: throw EntityNotFoundException("Location not found")
        locationRepository.delete(deletedLocation)
    }
}