package com.funproject.developer.funproject.controller

import com.funproject.developer.funproject.model.exception.*
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ResponseStatus


@RestControllerAdvice
class ExceptionController {

    @ExceptionHandler(BadCredentialsException::class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    fun handleBadCredentialsException(ex: BadCredentialsException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_ACCEPTABLE)
    }

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUserNotFoundException(ex: UserNotFoundException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleEntityNotFoundException(ex: EntityNotFoundException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

}