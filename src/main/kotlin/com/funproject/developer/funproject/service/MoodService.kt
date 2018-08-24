package com.funproject.developer.funproject.service

import com.funproject.developer.funproject.model.*
import com.funproject.developer.funproject.model.exception.EntityNotFoundException
import com.funproject.developer.funproject.repository.MoodRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class MoodService @Autowired constructor(
        private val moodRepository: MoodRepository
) {

    fun findAllMoods(): ArrayList<Mood> {
        return moodRepository!!.findAll()
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

}