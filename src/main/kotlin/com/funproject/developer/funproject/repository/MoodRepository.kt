package com.funproject.developer.funproject.repository
 
import com.funproject.developer.funproject.model.Mood
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface MoodRepository : JpaRepository<Mood, Long> {

	override fun findAll(): ArrayList<Mood>

}