package com.template.technocratia.data.utils

import android.util.Log
import com.template.technocratia.domain.utils.ErrorHandler
import com.template.technocratia.domain.utils.NetworkError
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor(): ErrorHandler {
    override fun getError(t: Throwable): NetworkError {
        return when (t) {
            is IOException -> NetworkError.NoInternetConnection("Connection is lost. Make sure you are connected to the internet.")
            is java.net.ConnectException -> NetworkError.NoInternetConnection("Connection is lost. Make sure you are connected to the internet.")
            is SocketTimeoutException -> NetworkError.ServiceUnavailable("Service isn't reachable, please try again later")
            else -> {
                Log.d("NetworkError", t.stackTraceToString())
                NetworkError.Unknown("Error, please try again later")
            }
        }
    }
}