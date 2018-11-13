package com.osfunapps.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.osfunapps.myapplication.client.ClientSide
import com.osfunapps.myapplication.server.ServerSide


class MainActivity : AppCompatActivity() {

    private var clientSide = ClientSide(this)
    private var serverSide = ServerSide(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvIP = "192.168.1.11"
        val port = 500
        //serverSide.openSocket()
        clientSide.firstConnectionAttempt(tvIP)
    }

}
