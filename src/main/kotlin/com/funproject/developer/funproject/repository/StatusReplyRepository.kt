package com.funproject.developer.funproject.repository
 
import com.funproject.developer.funproject.model.StatusReply
import com.funproject.developer.funproject.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface StatusReplyRepository : JpaRepository<StatusReply, Long> {

	override fun findAll(): ArrayList<StatusReply>

	@Query(value = "SELECT * FROM status_reply WHERE user_id != :id", nativeQuery = true)
	fun findAllNotPersonal(@Param("id") id: Long): ArrayList<StatusReply>

	@Query("SELECT sr	FROM StatusReply sr WHERE sr.publish_date = (SELECT MAX(publish_date) FROM StatusReply sb WHERE sr.user.id = sb.user.id )")
	fun findTeamStatuses(): ArrayList<StatusReply>


}