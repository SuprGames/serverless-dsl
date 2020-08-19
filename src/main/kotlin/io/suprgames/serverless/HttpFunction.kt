package io.suprgames.serverless

enum class HttpMethod {
    GET, POST, PUT, DELETE
}

annotation class HttpFunction(val name: String = "", val method: HttpMethod = HttpMethod.GET, val path: String)