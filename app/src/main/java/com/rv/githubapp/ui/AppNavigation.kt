package com.rv.githubapp.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rv.githubapp.ui.contributors.ContributorScreen
import com.rv.githubapp.ui.home.HomeScreen

@Composable
fun AppNavigation(
    start: String = Destination.HOME,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = start) {
        composable(route = Destination.HOME) { from ->
            HomeScreen(
                onRepoClick = {owner, repo ->
                    if (from.lifecycleIsResumed()) {
                        navController.navigate("${Destination.CONTRIBUTORS}/$owner/$repo")
                    }
                }
            )
        }

        composable(
            route = "${Destination.CONTRIBUTORS}/{${Arguments.OWNER}}/{${Arguments.REPO}}",
            arguments = listOf(
                navArgument(Arguments.OWNER) {
                    type = NavType.StringType
                },
                navArgument(Arguments.REPO) {
                    type = NavType.StringType
                }
            )
        ) { from ->
            val owner = from.arguments?.getString(Arguments.OWNER) ?: ""
            val repo = from.arguments?.getString(Arguments.REPO) ?: ""

            ContributorScreen(owner = owner, name = repo)
        }
    }
}

object Destination {
    const val HOME = "home"
    const val CONTRIBUTORS = "contributors"
}

object Arguments {
    const val OWNER = "owner"
    const val REPO = "repo"
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED