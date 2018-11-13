package com.osfunapps.myapplication.client.clientandserverlibrary

import java.net.URI
import java.nio.ByteBuffer

import org.java_websocket.client.WebSocketClient
import org.java_websocket.drafts.Draft
import org.java_websocket.handshake.ServerHandshake


public class SimpleClient : WebSocketClient {

    private lateinit var callback: SocketClientCallback

    fun setCallback(callback: SocketClientCallback){
        this.callback = callback
    }

    constructor(serverUri: URI, draft: Draft) : super(serverUri, draft)

    constructor(serverURI: URI) : super(serverURI)

    override fun onOpen(handshakedata: ServerHandshake) {
        callback.onClientSocketOpen()
    }

    override fun onClose(code: Int, reason: String, remote: Boolean) {
        callback.onClientSocketClose()
    }

    override fun onMessage(message: String) {
        callback.onClientSocketMessage(message)
    }

    override fun onMessage(message: ByteBuffer) {
        callback.onClientSocketMessage(message)
    }

    override fun onError(ex: Exception) {
        callback.onClientSocketError(ex)
    }

}

interface SocketClientCallback{
    fun onClientSocketOpen()
    fun onClientSocketClose()
    fun onClientSocketMessage(message: String)
    fun onClientSocketMessage(message: ByteBuffer)
    fun onClientSocketError(ex: Exception)
}