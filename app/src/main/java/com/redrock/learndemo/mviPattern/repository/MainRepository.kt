package com.redrock.learndemo.mviPattern.repository

import com.redrock.learndemo.mviPattern.network.ApiService
import com.redrock.learndemo.mviPattern.network.RetrofitBuilder.apiService

/**
 * Author by OkAndGreat
 * Date on 2021/11/15 23:51.
 *
 */
class MainRepository(
    private val apiService: ApiService
) {
    suspend fun getUsers() = apiService.getUsers()
}