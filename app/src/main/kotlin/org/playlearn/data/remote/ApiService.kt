package org.playlearn.data.remote

import org.playlearn.core.model.Content
import org.playlearn.core.model.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Body

interface ApiService {
    @GET("contents")
    suspend fun fetchContents(): List<Content>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: String): User

    @POST("users/register")
    suspend fun register(@Body user: User): User
}
