package de.amirrocker.testdagger2modules.home.data.account

import javax.inject.Inject

class AccountRepository @Inject constructor(
    val accountService: UserManager
) {
}