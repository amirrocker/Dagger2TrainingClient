package de.amirrocker.testdagger2modules.application

import android.app.Application

open class TestDagger2ModulesApplication : Application() {

    /**
     * the in-memory lifecycle bound di-graph for the application.
     *
     */
    val applicationComponent : ApplicationComponent by lazy {
        DaggerApplicationComponent
            .factory()
            .create(applicationContext)
    }

}