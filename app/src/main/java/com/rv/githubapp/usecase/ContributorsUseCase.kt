package com.rv.githubapp.usecase

import com.rv.githubapp.data.remote.model.ContributorsResponse
import com.rv.githubapp.repository.ContributorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContributorsUseCase @Inject constructor(
    private val contributorsRepo: ContributorsRepository
) {
    suspend fun getContributors(owner: String, repo: String) : List<ContributorModel> {
        return withContext(Dispatchers.IO) {
            contributorsRepo.getContributors(owner = owner, repo = repo)?.map {
                ContributorModel(
                    name = it.login,
                    image = it.avatarUrl,
                    url = it.htmlUrl
                )
            } ?: listOf()
        }
    }
}

data class ContributorModel(
    val name: String,
    val image: String,
    val url: String
)