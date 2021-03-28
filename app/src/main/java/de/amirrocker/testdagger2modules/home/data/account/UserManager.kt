package de.amirrocker.testdagger2modules.home.data.account

import de.amirrocker.testdagger2modules.base.data.store.ReactiveStore
import de.amirrocker.testdagger2modules.home.data.trainingSession.TrainingSession
import javax.inject.Inject
import javax.inject.Singleton

// @Singleton -> both MainActivity and RegistrationActivity graph use the same userManager instance
@Singleton
class UserManager @Inject constructor(
// TODO change this to a User instead of a trainingSession and provide it for dagger
//    private val storage:ReactiveStore<String, TrainingSession> // TODO change to Storage
) {

    fun registerUser(username:String, password:String) {
        // storage.storeSingular(username)
    }

}