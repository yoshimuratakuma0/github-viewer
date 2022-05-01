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

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: GithubUserDetailViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            GithubViewerTheme {
                NavHost(navController = navController, startDestination = "github_users_screen") {
                    composable(route = "github_users_screen") {
                        val viewModel: GithubUsersViewModel = hiltViewModel()
                        GithubUsersScreen(navController, viewModel)
                    }
                    composable(
                        route = "github_user_detail_screen/{username}",
                        arguments = listOf(
                            navArgument("username") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        backStackEntry.arguments?.getString("username")?.let { username ->
                            GithubUserDetailScreen(
                                viewModelFactory.create(username)
                            )
                        }
                    }
                }
            }
        }
    }
}
