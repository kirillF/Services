package ru.mail.park.advandroid.helloword.lection2

import android.app.IntentService
import android.content.Intent
import android.os.Looper
import android.os.Handler
import android.widget.Toast

class CommandService @JvmOverloads constructor(name: String = "Command Service") : IntentService(name) {
    private val mHandler = Handler(Looper.myLooper())

    override fun onHandleIntent(intent: Intent?) {
        try {
            Thread.sleep(2000)
            if (intent!!.action == ACTION) {
                mHandler.post { Toast.makeText(this@CommandService, "Hello, from CommandService", Toast.LENGTH_SHORT).show() }
            }
        } catch (e: InterruptedException) {

        }
    }

    companion object {
        const val ACTION = "COMMAND_SERVICE_ACTION"
    }
}
