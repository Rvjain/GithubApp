package com.rv.githubapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.rv.githubapp.ui.BaseViewState
import com.rv.githubapp.ui.repo.RepoViewModel
import com.rv.githubapp.usecase.RepoModel

@Composable
fun HomeScreen(
    viewModel: RepoViewModel = hiltViewModel(),
    onRepoClick: (owner: String, repo: String) -> Unit
) {
    val repoState: BaseViewState<RepoModel> = viewModel.state.collectAsState().value
    Scaffold {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when(repoState) {
                is BaseViewState.ERROR -> {

                }
                is BaseViewState.LOADING -> {
                    CircularProgressIndicator()
                }
                is BaseViewState.SUCCESS -> {
                    RepoList(
                        repos = repoState.data,
                        onRepoClick = onRepoClick
                    )
                }
            }
        }
    }
}

@Composable
fun RepoList(
    repos: List<RepoModel>,
    onRepoClick: (owner: String, repo: String) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(items = repos) { repo ->
            RepoCard(repo = repo, onRepoClick)
            Divider()
        }
    }
}

@Composable
fun RepoCard(
    repo: RepoModel,
    onRepoClick: (owner: String, repo: String) -> Unit
) {
    Row(modifier = Modifier
        .padding(horizontal = 8.dp, vertical = 8.dp)
        .fillMaxWidth()
        .clickable {
            onRepoClick.invoke(repo.owner, repo.name)
        },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = repo.name,
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = repo.gitLink,
                style = TextStyle(fontWeight = FontWeight.Normal),
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}