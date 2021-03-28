package de.amirrocker.testdagger2modules.base.common.precondition

import android.os.Looper
import java.util.*
import javax.inject.Inject

class AndroidPrecondition @Inject constructor() {

    fun assertWorkerThread() {
        when {
            isMainThread() -> throw IllegalStateException("This task must be run by a worker thread")
        }
    }

    fun assertUIThread() {
        when {
            !isMainThread() -> throw IllegalStateException("This task must be run by main thread thread")
        }
    }

    /**
     * return whether we are dealing with the UI Thread
     */
    fun isMainThread() =
        Objects.equals(Looper.getMainLooper().thread, Thread.currentThread())

}