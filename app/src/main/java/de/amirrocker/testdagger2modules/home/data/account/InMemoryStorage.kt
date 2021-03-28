package de.amirrocker.testdagger2modules.home.data.account

import android.content.Context
import javax.inject.Inject

class InMemoryStorage @Inject constructor(
    val context:Context
) : Storage {

    override fun store(some: String) {
        println("store some called with context: $context")
    }
}