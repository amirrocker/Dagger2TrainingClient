package de.amirrocker.testdagger2modules.application.scope

import javax.inject.Scope


/**
 * We want to scope RegistrationViewModel to RegistrationComponent.
 * we have to annotate both the class and the interface with @ActivityScope.
 *
 */
@Scope
@MustBeDocumented
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope()
