package de.amirrocker.testdagger2modules.login

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginRetrofitService {

//    TODO do something like this when needed
//    @POST()
//    fun login(username:String, password:String)

    @GET("/api/doLogin")
    fun doLogin()

}


