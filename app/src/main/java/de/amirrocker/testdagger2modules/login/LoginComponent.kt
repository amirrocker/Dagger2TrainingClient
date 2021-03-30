package de.amirrocker.testdagger2modules.login

import dagger.Subcomponent
import de.amirrocker.testdagger2modules.base.injection.scopes.ActivityScope

/*
We can annotate the LoginComponent with ActivityScope since the component will have the same lifetime as LoginActivity.
 */

@ActivityScope  // this graph is only available to activity login
@Subcomponent  // a Subcomponent since it is a 'child-graph' of ApplicationCompnent
interface LoginComponent {

    // the factory
    @Subcomponent.Factory
    interface Factory {
        fun create():LoginComponent
    }

    fun inject(loginActivity: LoginActivity)

    /**
     * the object injected with inject represents the root of the graph where
     * dagger will start scanning for @Inject annotations.
     */
    fun inject(loginFragment: LoginFragment)

//    /**
//     * the object injected with inject represents the root of the graph where
//     * dagger will start scanning for @Inject annotations.
//     */
//    fun inject(termsAndConditionsFragment: TermsAndConditionsFragment)

}