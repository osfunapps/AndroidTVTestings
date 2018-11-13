package com.osfunapps.myapplication.client

import android.content.Context
import android.view.View
import com.neovisionaries.ws.client.*
import com.osfunapps.myapplication.MainActivity
import com.osfunapps.myapplication.server.NaiveSSLContext

class ClientSide(private val context: Context) {


    val command = "{params: {\"TypeOfRemote\": SendRemoteKey, Cmd: \"Click\", DataOfCmd: \"KEY_LEFT\", Option: \"false\"}, method: \"ms.remote.control\"}"
    val url = "wss://192.168.1.102:8002/api/v2/channels/samsung.remote.control?name=c2Ftc3VuZ2N0bA=="


    fun firstConnectionAttempt(ip: String) {

        val ws = WebSocketFactory().createSocket(ip)


        // Register a listener to receive WebSocket events.
        ws.addListener(object : WebSocketAdapter() {

            override fun onConnected(websocket: WebSocket, headers: MutableMap<String, MutableList<String>>) {
                ws.sendText(command)
            }

            override fun onError(websocket: WebSocket?, cause: WebSocketException?) {
                super.onError(websocket, cause)
            }

            override fun onDisconnected(websocket: WebSocket?, serverCloseFrame: WebSocketFrame?, clientCloseFrame: WebSocketFrame?, closedByServer: Boolean) {
                super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer)
            }

            override fun onSendError(websocket: WebSocket?, cause: WebSocketException?, frame: WebSocketFrame?) {
                super.onSendError(websocket, cause, frame)
            }

            override fun onSendingHandshake(websocket: WebSocket?, requestLine: String?, headers: MutableList<Array<String>>?) {
                super.onSendingHandshake(websocket, requestLine, headers)
            }

        })
        Thread{
            ws.connect()
        }.start()
    }


    fun secondConnectionAttempt(ip: String, port: Int) {

        val factory = WebSocketFactory()

        // Create a custom SSL context.
        val context = NaiveSSLContext.getInstance("TLS")

        // Set the custom SSL context.
        factory.sslContext = context

        // Disable manual hostname verification for NaiveSSLContext.
        //
        // Manual hostname verification has been enabled since the
        // version 2.1. Because the verification is executed manually
        // after Socket.connect(SocketAddress, int) succeeds, the
        // hostname verification is always executed even if you has
        // passed an SSLContext which naively accepts any server
        // certificate. However, this behavior is not desirable in
        // some cases and you may want to disable the hostname
        // verification. You can disable the hostname verification
        // by calling WebSocketFactory.setVerifyHostname(false).

        //todo: check if enable/disable this
        //factory.verifyHostname = false
        //factory.setServerName("example.com");

        val ws = factory.createSocket(ip, port)

        // Register a listener to receive WebSocket events.
        ws.addListener(object : WebSocketAdapter() {

            override fun onConnected(websocket: WebSocket, headers: MutableMap<String, MutableList<String>>) {
                ws.sendText(command)
            }

            override fun onError(websocket: WebSocket?, cause: WebSocketException?) {
                super.onError(websocket, cause)
            }

            override fun onDisconnected(websocket: WebSocket?, serverCloseFrame: WebSocketFrame?, clientCloseFrame: WebSocketFrame?, closedByServer: Boolean) {
                super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer)
            }

            override fun onSendError(websocket: WebSocket?, cause: WebSocketException?, frame: WebSocketFrame?) {
                super.onSendError(websocket, cause, frame)
            }

            override fun onSendingHandshake(websocket: WebSocket?, requestLine: String?, headers: MutableList<Array<String>>?) {
                super.onSendingHandshake(websocket, requestLine, headers)
            }

        })
        Thread{
            ws.connect()
        }.start()
    }
}
