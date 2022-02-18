package com.rv.githubapp.data.remote.api

import com.rv.githubapp.data.remote.model.RepositoriesResponse
import com.rv.githubapp.data.remote.model.ContributorsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET(ROUTE_REPOSITORY)
    suspend fun getRepositories(@Query(QUERY_PARAM) query: String): Response<RepositoriesResponse>

    @GET(ROUTE_CONTRIBUTORS)
    suspend fun getContributors(@Path(PATH_PARAM_OWNER) owner: String, @Path(PATH_PARAM_REPO) repo: String): Response<List<ContributorsResponse.ContributorsResponseItem>>

    companion object {
        // may store it in build_config
        const val BASE_URL = "https://api.github.com/"
        const val ROUTE_REPOSITORY = "search/repositories"
        const val ROUTE_CONTRIBUTORS = "repos/{owner}/{repo}/contributors"
        const val QUERY_PARAM = "q"
        const val PATH_PARAM_OWNER = "owner"
        const val PATH_PARAM_REPO = "repo"
    }
}