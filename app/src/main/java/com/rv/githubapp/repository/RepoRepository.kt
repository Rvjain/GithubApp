package com.rv.githubapp.repository

import android.util.Log
import com.rv.githubapp.data.remote.api.GithubApi
import com.rv.githubapp.data.remote.model.RepositoriesResponse
import retrofit2.Response
import javax.inject.Inject

class RepoRepository @Inject constructor(
    private val api: GithubApi
) {

    suspend fun getRepositories(query: String) : RepositoriesResponse? {
        val response = api.getRepositories(query = query)
        return if (response.isSuccessful) {
            return response.body()
        } else {
            Log.i(TAG, "Error while getting the response from github repositories")
            Log.e(TAG, "Error code - ${response.code()} message - ${response.message()}")
            null
        }
    }

    companion object {
        const val TAG = "RepoRepository"
    }
}