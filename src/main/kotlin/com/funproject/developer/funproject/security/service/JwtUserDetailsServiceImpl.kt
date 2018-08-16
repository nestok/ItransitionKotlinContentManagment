package com.funproject.developer.funproject.security.service

import com.funproject.developer.funproject.model.exception.UserNotFoundException
import com.funproject.developer.funproject.repository.UserRepository
import com.funproject.developer.funproject.security.model.JwtUserDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtUserDetailsServiceImpl @Autowired constructor(
        private val userRepository: UserRepository
) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val byUsername = this.userRepository!!.findByUsername(username)

        return Optional.ofNullable<Any>(byUsername)
                .map({ JwtUserDetails(byUsername!!) })
                .orElseThrow({ UserNotFoundException("User " + username + " not found.")  })
    }
}
