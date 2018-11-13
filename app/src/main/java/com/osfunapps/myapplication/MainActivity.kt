package com.osfunapps.myapplication

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.osfunapps.myapplication.client.clientandserverlibrary.SimpleClient
import com.osfunapps.myapplication.server.clientandserverlibrary.SimpleServer
import com.osfunapps.myapplication.client.onlyclientlibrary.ClientSide
import java.net.InetSocketAddress
import java.net.URI
import kotlin.concurrent.thread
import android.text.format.Formatter.formatIpAddress
import android.net.wifi.WifiManager
import android.text.format.Formatter
import android.text.format.Formatter.formatIpAddress
import com.osfunapps.myapplication.client.clientandserverlibrary.SocketClientCallback
import com.osfunapps.myapplication.server.clientandserverlibrary.SocketServerCallback
import java.nio.ByteBuffer


private var port: Int = 8887
private lateinit var ip: String


class MainActivity : AppCompatActivity(), SocketServerCallback, SocketClientCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        obtainIpAddress()
        initServer()
        //initClient()
    }


    private fun obtainIpAddress() {
        val wm = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        ip = Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
    }

    private lateinit var server: SimpleServer


    private fun initServer() {
        Thread {

            server = SimpleServer(this, InetSocketAddress(ip, port));
            server.run();
        }.start()
    }


    private fun initClient() {
        Thread {
            val client = SimpleClient(URI("ws://$ip:${port}"))
            client.setCallback(this)
            client.connect()
        }.start()
    }

    override fun onClientSocketOpen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClientSocketClose() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClientSocketMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClientSocketMessage(message: ByteBuffer) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClientSocketError(ex: Exception) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerSocketOpen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerSocketClose(reason: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerSocketMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerSocketMessage(message: ByteBuffer) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerSocketError(ex: Exception) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerSocketStart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
