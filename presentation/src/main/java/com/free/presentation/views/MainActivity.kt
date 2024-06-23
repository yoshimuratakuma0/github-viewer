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
import com.free.presentation.viewmodels.GithubUserDetailViewModel.Companion.KEY_USERNAME
import com.free.presentation.views.theme.GithubViewerTheme
import dagger.hilt.android.AndroidEntryPoint


object ScreenRoutes {
    const val GITHUB_USERS = "github_users_screen/"
    const val GITHUB_USER_DETAIL = "github_user_detail_screen/"
    const val GITHUB_FOLLOWING = "github_following/"
    const val GITHUB_FOLLOWERS = "github_followers/"
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubViewerTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ScreenRoutes.GITHUB_USERS
                ) {
                    composable(route = ScreenRoutes.GITHUB_USERS) {
                        GithubUsersScreen(
                            hiltViewModel(),
                            onClickUser = { username ->
                                navController.navigate("${ScreenRoutes.GITHUB_USER_DETAIL}$username")
                            },
                            onFollowers = { username ->
                                navController.navigate("${ScreenRoutes.GITHUB_FOLLOWERS}$username")
                            },
                            onFollowing = { username ->
                                navController.navigate("${ScreenRoutes.GITHUB_FOLLOWING}$username")
                            },
                        )
                    }
                    composable(
                        route = "${ScreenRoutes.GITHUB_USER_DETAIL}{$KEY_USERNAME}",
                        arguments = listOf(
                            navArgument(KEY_USERNAME) { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        backStackEntry.arguments?.getString(KEY_USERNAME)?.let {
                            GithubUserDetailScreen(
                                hiltViewModel(),
                            ) {
                                navController.popBackStack()
                            }
                        }
                    }
                    composable(
                        route = "${ScreenRoutes.GITHUB_FOLLOWING}{$KEY_USERNAME}",
                        arguments = listOf(
                            navArgument(KEY_USERNAME) { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        backStackEntry.arguments?.getString(KEY_USERNAME)?.let {
                            GitHubFollowingScreen(
                                hiltViewModel(),
                                onClickUser = {
                                    navController.popBackStack()
                                },
                                onFollowers = { username ->
                                    navController.navigate("${ScreenRoutes.GITHUB_FOLLOWERS}$username")
                                },
                                onFollowing = { username ->
                                    navController.navigate("${ScreenRoutes.GITHUB_FOLLOWING}$username")
                                },
                                onBackPressed = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }

                    composable(
                        route = "${ScreenRoutes.GITHUB_FOLLOWERS}{$KEY_USERNAME}",
                        arguments = listOf(
                            navArgument(KEY_USERNAME) { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        backStackEntry.arguments?.getString(KEY_USERNAME)?.let {
                            GitHubFollowersScreen(
                                hiltViewModel(),
                                onClickUser = {
                                    navController.popBackStack()
                                },
                                onFollowers = { username ->
                                    navController.navigate("${ScreenRoutes.GITHUB_FOLLOWERS}$username")
                                },
                                onFollowing = { username ->
                                    navController.navigate("${ScreenRoutes.GITHUB_FOLLOWING}$username")
                                },
                                onBackPressed = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
