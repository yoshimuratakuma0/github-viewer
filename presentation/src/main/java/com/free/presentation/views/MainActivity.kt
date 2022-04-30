package com.free.presentation.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.free.presentation.viewmodels.GithubUserDetailViewModel
import com.free.presentation.viewmodels.GithubUsersViewModel
import com.free.presentation.views.theme.GithubViewerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            GithubViewerTheme {
                NavHost(navController = navController, startDestination = "screen1") {
                    composable(route = "screen1") {
                        val viewModel: GithubUsersViewModel = hiltViewModel()
                        GithubUsersScreen(viewModel)
                    }
                    composable(route = "screen2") {
                        val viewModel: GithubUserDetailViewModel = hiltViewModel()
                        GithubUserDetailScreen(viewModel)
                    }
                }
            }
        }
    }
}
