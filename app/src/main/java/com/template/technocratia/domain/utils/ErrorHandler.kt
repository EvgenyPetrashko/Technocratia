package com.template.technocratia.domain.utils

interface ErrorHandler {
    fun getError(t: Throwable): NetworkError
}