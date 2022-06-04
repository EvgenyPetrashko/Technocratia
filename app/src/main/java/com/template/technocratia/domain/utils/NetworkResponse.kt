package com.template.technocratia.domain.utils

sealed class NetworkResponse<T> {
    class Success<T>(val data: T) : NetworkResponse<T>()
    class Failure<T>(val error: NetworkError) : NetworkResponse<T>()
}

sealed class NetworkError(val msg: String) {
    class NoInternetConnection(errorMsg: String) : NetworkError(errorMsg)

    class ServiceUnavailable(errorMsg: String) : NetworkError(errorMsg)

    class AccessDenied(errorMsg: String) : NetworkError(errorMsg)

    class Unknown(errorMsg: String) : NetworkError(errorMsg)

}