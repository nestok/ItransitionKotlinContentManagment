package com.funproject.developer.funproject.service

import com.funproject.developer.funproject.model.*
import com.funproject.developer.funproject.model.exception.EntityNotFoundException
import com.funproject.developer.funproject.model.exception.EntityUsesInDBException
import com.funproject.developer.funproject.repository.MoodRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.Exception


interface MoodService {

    fun findAllMoods(): ArrayList<Mood>

    fun addMood(mood: Mood)

    fun editMood(mood: Mood)

    fun deleteMood(id: Long)

}