package de.amirrocker.testdagger2modules.registration

import de.amirrocker.testdagger2modules.base.injection.scopes.ActivityScope
import de.amirrocker.testdagger2modules.home.data.account.UserManager
import javax.inject.Inject


@ActivityScope
class RegistrationViewModel @Inject constructor(
    val userManager: UserManager
) {

    companion object {
        private const val None = "None2"
    }

    private var username: String = None
    private var password: String = None
    private var acceptTCs : Boolean = true

    fun acceptTCs() {
        acceptTCs = true
    }

    fun registerUser() {
        assert(username.isNotBlank().and(!password.equals("None")))
        assert(password.isNotBlank().and(!password.equals("None")))
        assert(acceptTCs)

        userManager.registerUser(username, password)
    }

}