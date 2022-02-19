package com.rv.githubapp.repository

import android.util.Log
import com.rv.githubapp.data.remote.api.GithubApi
import com.rv.githubapp.data.remote.model.ContributorsResponse
import javax.inject.Inject

class ContributorsRepository @Inject constructor(
    private val api: GithubApi
) {
    /**
     * Fetches the contributors for the [owner] and [repo]
     * OnError we log and return an empty list
     * @return [List] of [ContributorsResponse.ContributorsResponseItem]
     */
    suspend fun getContributors(owner: String, repo: String) : List<ContributorsResponse.ContributorsResponseItem>? {
        val response = api.getContributors(owner = owner, repo = repo)
        return if (response.isSuccessful) {
            return response.body()
        } else {
            Log.i(TAG, "Error while getting the response from github contributors")
            Log.e(TAG, "Error code - ${response.code()} message - ${response.message()}")
            null
        }
    }

    companion object {
        const val TAG = "ContributorsRepository"
    }
}