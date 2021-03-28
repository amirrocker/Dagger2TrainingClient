package de.amirrocker.testdagger2modules.application

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(
    val app:TestDagger2ModulesApplication
) {

    @Provides
    @Singleton
    fun providesApp() = app


    /*
    * TODO find out whether and if so why this approach is considered bad. use @BindsInstance in AppComponent instead
    */
//    @Provides
//    fun providesContext():Context = app

}