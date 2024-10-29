package tn.esprit.nascar.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import tn.esprit.nascar.models.User

interface UserAPI {

    @GET("users/random")
    fun getUsers(): Call<List<User>>

    @POST("login")
    fun login(@Body user: User): Call<User>
}