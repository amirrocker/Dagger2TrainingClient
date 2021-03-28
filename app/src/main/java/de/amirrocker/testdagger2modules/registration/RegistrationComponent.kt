package de.amirrocker.testdagger2modules.registration

import dagger.Subcomponent
import de.amirrocker.testdagger2modules.application.scope.ActivityScope

/**
 * Now that both Types have received the same @Scope - this component will
 * always provide the same instance of RegistrationViewModel
 *
 */
@ActivityScope
@Subcomponent
interface RegistrationComponent {

    /**
     * Factory will be available as DaggerRegistrationComponent.Factory().create()
     */
    @Subcomponent.Factory
    interface Factory {
        fun create():RegistrationComponent
    }

    /**
     * the injected object represents a leaf in the graph with ApplicationComponent being the
     * root and with Subcomponents below to represent all different branches the instance graph.
     */
    fun inject(registrationActivity: RegistrationActivity)

    /**
     * the object injected with inject represents the root of the graph where
     * dagger will start scanning for @Inject annotations.
     */
    fun inject(enterDetailsFragment: EnterDetailsFragment)

    /**
     * the object injected with inject represents the root of the graph where
     * dagger will start scanning for @Inject annotations.
     */
    fun inject(termsAndConditionsFragment: TermsAndConditionsFragment)


}