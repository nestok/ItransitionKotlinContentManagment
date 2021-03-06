package com.funproject.developer.funproject.repository
 
import com.funproject.developer.funproject.model.Location
import com.funproject.developer.funproject.model.Mood
import com.funproject.developer.funproject.model.StatusReply
import com.funproject.developer.funproject.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface LocationRepository : JpaRepository<Location, Long> {

	override fun findAll(): ArrayList<Location>

}