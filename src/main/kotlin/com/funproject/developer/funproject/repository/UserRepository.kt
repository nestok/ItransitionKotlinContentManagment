package com.funproject.developer.funproject.repository
 
import com.funproject.developer.funproject.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : JpaRepository<User, Long> {

	fun findByUsername(username: String): User?
    fun findByEmail(email: String): User?

    @Query("SELECT u FROM User u WHERE u.is_deleted = false")
    fun findAllExisted(): ArrayList<User>
}