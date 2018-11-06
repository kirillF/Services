package ru.mail.technotrack.services

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.service_activity.*
import ru.mail.technotrack.services.lection2.ICallback
import ru.mail.technotrack.services.lection2.ITestService
import ru.mail.technotrack.services.lection2.R

class ServiceActivity : AppCompatActivity() {

    private var mTestService: ITestService? = null

    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            mTestService = ITestService.Stub.asInterface(service)
            try {
                mTestService!!.bindActivity(mServiceCallback)
                mTestService!!.getStringAsync()
                connect.text = "Connected"
            } catch (e: RemoteException) {
                textView.text = "Service connection error"
                Log.e("ServiceActivity", "Can not get string from service", e)
            }

        }

        override fun onServiceDisconnected(name: ComponentName) {
        }
    }

    private val mServiceCallback = ServiceCallback()

    private inner class ServiceCallback : ICallback.Stub() {
        @Throws(RemoteException::class)
        override fun onNewString(data: String) {
            runOnUiThread { textView.text = data }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.service_activity)

        connect.setOnClickListener {
            if (mTestService == null) {
                val i = Intent(this@ServiceActivity, TestService::class.java)
                bindService(i, mServiceConnection, Context.BIND_AUTO_CREATE)
            } else {
                disconnect()
            }
        }

        sendCommand.setOnClickListener {
            val i = Intent(this@ServiceActivity, CommandService::class.java)
            i.action = CommandService.ACTION
            startService(i)
        }
    }

    private fun disconnect() {
        if (mTestService != null) {
            unbindService(mServiceConnection)
            mTestService = null
            textView.text = ""
            connect.text = "Connect"
        }
    }

    override fun onStart() {
        super.onStart()
        textView.text = ""
        connect.text = "Connect"
    }

    override fun onStop() {
        disconnect()
        super.onStop()
    }
}
