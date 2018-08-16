package com.funproject.developer.funproject.configuration

import com.funproject.developer.funproject.security.handler.RestAccessDeniedHandler
import com.funproject.developer.funproject.security.handler.RestAuthenticationEntryPoint
import com.funproject.developer.funproject.security.service.JwtAuthenticationFilter
import com.funproject.developer.funproject.security.service.JwtAuthenticationProvider
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
class SecurityConfiguration @Autowired constructor(
        private val userDetailsService: UserDetailsService,
        private val jwtAuthenticationProvider: JwtAuthenticationProvider
        ): WebSecurityConfigurerAdapter() {

    @Autowired
    @Throws(Exception::class)
    fun configureAuthentication(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder
                .userDetailsService<UserDetailsService>(this.userDetailsService)
                .passwordEncoder(passwordEncoder())
    }


    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .and()
                .csrf().disable()
                .addFilterAfter(JwtAuthenticationFilter(authenticationManagerBean()),
                        BasicAuthenticationFilter::class.java)
                .exceptionHandling()
                .authenticationEntryPoint(RestAuthenticationEntryPoint())
                .accessDeniedHandler(RestAccessDeniedHandler())
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity?) {
        web!!.ignoring()
                .antMatchers(HttpMethod.POST, "/auth/**", "/reply/getAll", "/reply/getMoods")
                .antMatchers(HttpMethod.GET, "/auth/**", "/reply/getAll", "/reply/getMoods")
                .antMatchers(HttpMethod.OPTIONS, "/**")
    }

    @Throws(Exception::class)
    override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder?) {
        authenticationManagerBuilder!!
                .authenticationProvider(this.jwtAuthenticationProvider)
                .userDetailsService<UserDetailsService>(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder())
    }

    companion object {

        private val ingoringUrls = arrayOf("/auth/**")
    }
}