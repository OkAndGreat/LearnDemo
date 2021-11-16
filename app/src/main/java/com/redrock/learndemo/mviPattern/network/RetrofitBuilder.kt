package com.redrock.learndemo.mviPattern.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Author by OkAndGreat，Date on 2021/11/15.
 *
 */
object RetrofitBuilder {

    private const val BASE_URL = "https://reqres.in/api/user/1"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()


    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}