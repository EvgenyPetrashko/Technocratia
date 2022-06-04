package com.template.technocratia.domain.entities

data class UserWrapper<T>(
    val error: String?,
    val data: T?
) {
}