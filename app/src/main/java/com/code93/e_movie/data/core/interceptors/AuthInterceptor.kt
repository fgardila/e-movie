package com.code93.e_movie.data.core.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("accept", "application/json")
            .header("Authorization", "Bearer $API_READ_ACCESS_TOKEN")
            .build()
        return chain.proceed(request)
    }

    companion object {
        private const val API_READ_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNmUxZGY2NTcyNzEzMTAzODNmOTUxZjJmNzY0N2U5NiIsInN1YiI6IjYzMzYzOTQ5NmUwZDcyMDA5YjZhYzcxMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.qorIczYJQcmkN51Znv5gjHMXAzMxYPAjDtniJDFXMxI"
    }
}