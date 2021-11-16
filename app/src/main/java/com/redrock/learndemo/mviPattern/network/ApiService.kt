package com.redrock.learndemo.mviPattern.network

import com.redrock.learndemo.mviPattern.bean.User
import retrofit2.http.GET

/**
 * Author by OkAndGreat，Date on 2021/11/15.
 *
 */

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
}

