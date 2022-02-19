package com.rv.githubapp.usecase

import com.rv.githubapp.data.remote.model.RepositoriesResponse
import com.rv.githubapp.repository.RepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepoUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) {

    /**
     * Get Top Started Repos returns top [count] list of [RepoModel]
     */
    suspend fun getTopStartedRepositories(count: Int = 100) : List<RepoModel> {
        return withContext(Dispatchers.IO) {
            repoRepository.getRepositories(DEFAULT_QUERY)?.items?.take(count)?.map {
                RepoModel(
                    name = it.name,
                    gitLink = it.htmlUrl,
                    owner = it.owner.login,

                )
            } ?: listOf()
        }
    }

    companion object {
        const val DEFAULT_QUERY = "stars:>0"
    }
}

data class RepoModel(
    val name: String,
    val gitLink: String,
    val owner: String,
)