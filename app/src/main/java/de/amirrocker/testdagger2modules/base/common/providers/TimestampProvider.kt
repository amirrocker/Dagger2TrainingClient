package de.amirrocker.testdagger2modules.base.common.providers

import javax.inject.Inject

class TimestampProvider @Inject constructor() {

    fun currentTimeMillis():Long = System.currentTimeMillis()

}