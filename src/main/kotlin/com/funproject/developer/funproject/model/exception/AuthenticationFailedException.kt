package com.funproject.developer.funproject.model.exception

class AuthenticationFailedException : RuntimeException {

    constructor(message: String) : super(message) {}

    constructor(message: String, exception: Throwable) : super(message, exception) {}
}
