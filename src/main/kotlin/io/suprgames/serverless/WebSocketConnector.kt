package io.suprgames.serverless

class WebSocketRoutes {
    companion object {
        const val CONNECT = "\$connect"
        const val DEFAULT = "\$default"
        const val DISCONNECT = "\$disconnect"
    }
}

annotation class WebSocketConnector(val name: String = "", val route: String)