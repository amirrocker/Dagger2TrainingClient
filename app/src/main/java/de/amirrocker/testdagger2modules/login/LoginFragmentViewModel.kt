package de.amirrocker.testdagger2modules.login

import de.amirrocker.testdagger2modules.login.domain.AuthenticationStore
import javax.inject.Inject

class LoginFragmentViewModel @Inject constructor(
    val authenticationStore: AuthenticationStore
) {

    fun login( username:String, password: String ):LoginResultDTO =
        authenticationStore.authenticate(username, password)




}