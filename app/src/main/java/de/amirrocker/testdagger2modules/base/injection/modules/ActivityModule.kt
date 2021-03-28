package de.amirrocker.testdagger2modules.base.injection.modules

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import de.amirrocker.testdagger2modules.base.injection.qualifiers.ForActivity

@Module
class ActivityModule(val activity: AppCompatActivity) {

    @ForActivity
    @Provides
    fun provideContext() = activity

    @Provides
    fun provideFragmentManager(activity: AppCompatActivity):FragmentManager =
        activity.supportFragmentManager

}