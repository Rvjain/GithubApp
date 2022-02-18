package com.rv.githubapp.ui.contributors

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.rv.githubapp.ui.BaseViewState
import com.rv.githubapp.usecase.ContributorModel

@Composable
fun ContributorScreen(
    owner: String,
    name: String,
    viewModel: ContributorViewModel = hiltViewModel()
) {
    val state: BaseViewState<ContributorModel> = viewModel.state.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        viewModel.loadContributors(owner, name)
    }

    Scaffold {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when(state) {
                is BaseViewState.ERROR -> {
                    Text(text = state.message)
                }
                is BaseViewState.LOADING -> {
                    CircularProgressIndicator()
                }
                is BaseViewState.SUCCESS -> {
                    ContributorsList(contributors = state.data)
                }
            }
        }
    }
}

@Composable
fun ContributorsList(
    contributors: List<ContributorModel>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        items(items = contributors) { contributor ->
            ContributorCard(contributor = contributor)
            Divider()
        }
    }
}

@Composable
fun ContributorCard(
    contributor: ContributorModel
) {
    val uriHandler = LocalUriHandler.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            .clickable {
                uriHandler.openUri(contributor.url)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = rememberImagePainter(
                data = contributor.image,
                builder = {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
            ),
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = Modifier
                .size(90.dp)
                .padding(horizontal = 8.dp),

        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = contributor.name,
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = contributor.url,
                style = TextStyle(fontWeight = FontWeight.Normal),
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}