package de.amirrocker.testdagger2modules.login

data class LoginResultDTO(
    val isLoggedIn : Boolean,
    val sessionId : String,
    val userid : String
) {
}