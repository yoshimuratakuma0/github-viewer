package com.free.presentation.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.free.presentation.viewmodels.GithubUserDetailViewModel
import com.free.presentation.viewmodels.GithubUsersViewModel
import com.free.presentation.views.theme.GithubViewerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


object ScreenRoutes {
    const val githubUsers = "github_users_screen/"
    const val githubUserDetail = "github_user_detail_screen/"
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: GithubUserDetailViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubViewerTheme {
                val userDetailKey = "username"
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ScreenRoutes.githubUsers
                ) {
                    composable(route = ScreenRoutes.githubUsers) {
                        val viewModel: GithubUsersViewModel = hiltViewModel()
                        GithubUsersScreen(viewModel, navController)
                    }
                    composable(
                        route = "${ScreenRoutes.githubUserDetail}{$userDetailKey}",
                        arguments = listOf(
                            navArgument(userDetailKey) { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        backStackEntry.arguments?.getString(userDetailKey)?.let { username ->
                            GithubUserDetailScreen(
                                navController,
                                viewModelFactory.create(username)
                            )
                        }
                    }
                }
            }
        }
    }
}
