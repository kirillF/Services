package ru.mail.technotrack.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException
import ru.mail.technotrack.services.lection2.ICallback
import ru.mail.technotrack.services.lection2.ITestService

import java.util.concurrent.TimeUnit

class TestService : Service() {

    private val mServiceProxy = TestServiceProxy()

    private inner class TestServiceProxy : ITestService.Stub() {
        private var mActivityCallbak: ICallback? = null

        @Throws(RemoteException::class)
        override fun getString(): String {
            return sayHello()
        }

        @Throws(RemoteException::class)
        override fun bindActivity(callback: IBinder) {
            mActivityCallbak = ICallback.Stub.asInterface(callback)
        }

        override fun getStringAsync() {
            sendString("Hello from Service!")
        }

        fun sendString(data: String) {
            Thread(Runnable {
                if (mActivityCallbak != null) {
                    try {
                        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
                        mActivityCallbak!!.onNewString(data)
                    } catch (e: InterruptedException) {

                    } catch (e: RemoteException) {

                    }

                }
            }).start()
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return mServiceProxy
    }

    fun sayHello(): String {
        return "Hello from Service!"
    }
}
