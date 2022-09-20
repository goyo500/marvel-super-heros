package es.plexus.android.data.common.logger

import android.util.Log

enum class SyncLogLevel { INFO, ERROR; }

class SyncLogger : Logger {
    override fun log(message: String, logLevel: SyncLogLevel) {
        val tag = "SyncLogger"

        when (logLevel) {
            SyncLogLevel.INFO -> Log.i(tag, message)
            SyncLogLevel.ERROR -> Log.e(tag, message)
        }
    }
}

interface Logger {
    fun log(message: String, logLevel: SyncLogLevel = SyncLogLevel.INFO)
}