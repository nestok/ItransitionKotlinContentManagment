package com.funproject.developer.funproject.configuration

import com.funproject.developer.funproject.security.model.JwtUserDetails
import com.funproject.developer.funproject.security.service.AuthenticationHelper
import com.funproject.developer.funproject.security.service.JwtUserDetailsServiceImpl
import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.context.SecurityContextHolder

@Configuration
class InterceptorConfig @Autowired constructor(
        private val authenticationHelper: AuthenticationHelper
) {

    @Bean
    fun requestTokenBearerInterceptor(): RequestInterceptor {
        return RequestInterceptor { requestTemplate ->
            val details = SecurityContextHolder.getContext().authentication.details as JwtUserDetails
            val token = authenticationHelper.generateToken(details.id!!)
            requestTemplate.header("Authorization", "bearer $token")
        }
    }

}