package com.rv.githubapp.api

import retrofit2.Response
import retrofit2.http.GET

interface GithubApi {

    @GET("")
    suspend fun somefun(): Response<String>
}