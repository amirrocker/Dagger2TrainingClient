package de.amirrocker.testdagger2modules.login.domain

import de.amirrocker.testdagger2modules.login.LoginResultDTO
import javax.inject.Inject

class AuthenticationStore @Inject constructor(
) {

    fun authenticate(username:String, password:String): LoginResultDTO =
        LoginResultDTO(true, "123", "userid")



}