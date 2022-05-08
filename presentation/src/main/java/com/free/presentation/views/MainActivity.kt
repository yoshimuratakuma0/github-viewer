package com.free.presentation.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.free.presentation.R
import com.free.presentation.viewmodels.GithubUserDetailViewModel
import com.free.presentation.viewmodels.GithubUsersViewModel
import com.free.presentation.views.theme.GithubViewerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

object ScreenRoutes {
    const val githubUsers = "github_users_screen/"
    const val githubUserDetail = "github_user_detail_screen/"
    const val settings = "settings/"
}

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object GithubUsers : Screen(
        ScreenRoutes.githubUsers,
        R.string.bottom_nav_title_users,
        Icons.Default.AccountCircle
    )

    object Settings : Screen(
        ScreenRoutes.settings,
        R.string.bottom_nav_title_settings,
        Icons.Default.Settings
    )
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
                Scaffold(
                    bottomBar = {
                        val items = listOf(
                            Screen.GithubUsers,
                            Screen.Settings,
                        )
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            items.forEach { screen ->
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            screen.icon,
                                            contentDescription = null
                                        )
                                    },
                                    label = { Text(stringResource(screen.resourceId)) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // reselecting the same item
                                            launchSingleTop = true
                                            // Restore state when reselecting a previously selected item
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = Screen.GithubUsers.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(route = Screen.GithubUsers.route) {
                            val viewModel: GithubUsersViewModel = hiltViewModel()
                            GithubUsersScreen(viewModel, navController)
                        }
                        composable(route = Screen.Settings.route) {
                            SettingsScreen()
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
}
