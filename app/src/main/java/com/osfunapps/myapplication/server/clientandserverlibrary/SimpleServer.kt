package com.osfunapps.myapplication.server.clientandserverlibrary

/**
 * Created by osapps on 13/11/2018.
 */

import java.net.InetSocketAddress
import java.nio.ByteBuffer

import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer

class SimpleServer(private val callback: SocketServerCallback,
                   address: InetSocketAddress) : WebSocketServer(address) {


    override fun onOpen(conn: WebSocket, handshake: ClientHandshake) {
        callback.onServerSocketOpen()
        conn.send("Welcome to the server!") //This method sends a message to the new client
        broadcast("new connection: " + handshake.resourceDescriptor) //This method sends a message to all clients connected
        println("new connection to " + conn.remoteSocketAddress)
    }

    override fun onClose(conn: WebSocket, code: Int, reason: String, remote: Boolean) {
        println("closed " + conn.remoteSocketAddress + " with exit code " + code + " additional info: " + reason)
        callback.onServerSocketClose(reason)
    }

    override fun onMessage(conn: WebSocket, message: String) {
        callback.onServerSocketMessage(message)

    }

    override fun onMessage(conn: WebSocket?, message: ByteBuffer) {
        println("received ByteBuffer from " + conn!!.remoteSocketAddress)
        callback.onServerSocketMessage(message)
    }

    override fun onError(conn: WebSocket, ex: Exception) {
        callback.onServerSocketError(ex)
    }

    override fun onStart() {
        callback.onServerSocketStart()
    }
}

interface SocketServerCallback{
    fun onServerSocketOpen()
    fun onServerSocketClose(reason: String)
    fun onServerSocketMessage(message: String)
    fun onServerSocketMessage(message: ByteBuffer)
    fun onServerSocketError(ex: Exception)
    fun onServerSocketStart()
}