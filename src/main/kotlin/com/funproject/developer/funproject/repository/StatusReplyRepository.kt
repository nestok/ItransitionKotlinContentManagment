package com.funproject.developer.funproject.repository
 
import com.funproject.developer.funproject.model.StatusReply
import com.funproject.developer.funproject.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface StatusReplyRepository : JpaRepository<StatusReply, Long> {

	override fun findAll(): ArrayList<StatusReply>

}