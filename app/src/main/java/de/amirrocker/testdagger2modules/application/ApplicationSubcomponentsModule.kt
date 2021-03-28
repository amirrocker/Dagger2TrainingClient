package de.amirrocker.testdagger2modules.application

import dagger.Module
import de.amirrocker.testdagger2modules.login.LoginComponent
import de.amirrocker.testdagger2modules.registration.RegistrationComponent

@Module(subcomponents = [RegistrationComponent::class, LoginComponent::class])
class ApplicationSubcomponentsModule {
}