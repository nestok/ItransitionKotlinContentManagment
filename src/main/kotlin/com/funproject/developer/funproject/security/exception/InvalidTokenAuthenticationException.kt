package com.funproject.developer.funproject.security.exception

import org.springframework.security.core.AuthenticationException

class InvalidTokenAuthenticationException : AuthenticationException {

    constructor(msg: String, throwable: Throwable) : super(msg, throwable)

    constructor(msg: String) : super(msg)

}
