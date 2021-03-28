package de.amirrocker.testdagger2modules.application

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import de.amirrocker.testdagger2modules.MainActivity
import de.amirrocker.testdagger2modules.home.injection.MainViewFragmentModule
import de.amirrocker.testdagger2modules.home.injection.MainViewFragmentViewModelModule
import de.amirrocker.testdagger2modules.home.presentation.MainViewFragment
import de.amirrocker.testdagger2modules.login.LoginComponent
import de.amirrocker.testdagger2modules.registration.RegistrationComponent
import de.amirrocker.testdagger2modules.training.sessiondetails.presentation.TrainingSessionDetailsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [PersistenceModule::class, NetworkModule::class, MainViewFragmentViewModelModule::class, MainViewFragmentModule::class, ApplicationSubcomponentsModule::class])
interface ApplicationComponent {

    /**
     * context is an android created object - it already exists outside the
     * graph - so dagger must not try to create its own instance but instead
     * pass the existing context to the graph.
     *
     */
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context):ApplicationComponent
    }

    /**
     * the object injected with inject represents the root of the graph where
     * dagger will start scanning for @Inject annotations.
     */
    fun inject(mainActivity: MainActivity)

    /**
     * tell dagger that we want a injection graph for this fragment
     */
    fun inject(mainViewFragment: MainViewFragment)

    /**
     * tell dagger that we want a injection graph for this fragment
     */
    fun inject(trainingSessionDetailsFragment: TrainingSessionDetailsFragment)

    fun getRegistrationComponent():RegistrationComponent.Factory

    fun getLoginComponent():LoginComponent.Factory

}