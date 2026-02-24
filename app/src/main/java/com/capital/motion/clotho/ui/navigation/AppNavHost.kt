package com.capital.motion.clotho.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.capital.motion.clotho.ui.screens.AIChatScreen
import com.capital.motion.clotho.ui.screens.AstrologyDetailScreen
import com.capital.motion.clotho.ui.screens.AstrologyViewModel
import com.capital.motion.clotho.ui.screens.DashboardScreen
import com.capital.motion.clotho.ui.screens.SetBirthPlace
import com.capital.motion.clotho.ui.screens.SetBirthTime
import com.capital.motion.clotho.ui.screens.SetBirthdate

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    // Single shared ViewModel — survives navigation between dashboard and detail
    val astrologyViewModel: AstrologyViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "setBirthdate",
    ) {


//         ───────────────── Dashboard ─────────────────
        composable(
            route = "dashboard",
            enterTransition = {
                // Dashboard comes IN from LEFT
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(800)
                )
            },
            exitTransition = {
                // Dashboard goes OUT to LEFT on back
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(800)
                )
            }
        ) {
            DashboardScreen(
                navController = navController,
                viewModel = astrologyViewModel
            )
        }

        // ── Dashboard
//        composable("dashboard") {
//            DashboardScreen(
//                navController = navController,
//                viewModel = astrologyViewModel
//            )
//        }

        composable("setBirthTime") {
            SetBirthTime(
                navController = navController)
        }

        composable("setBirthdate") {
            SetBirthdate(
                navController = navController)
        }

        composable("setBirthPlace") {
            SetBirthPlace(
                navController = navController)
        }

        // ── Astrology Detail ──────────────────────────────────────────────────
        composable("astrology_detail") {
            val selectedCard by astrologyViewModel.selectedCard.collectAsState()

            selectedCard?.let { card ->
                AstrologyDetailScreen(
                    data = card,
                    onBack = {
                        astrologyViewModel.clearCard()
                        navController.popBackStack()
                    }
                )
            }
        }


        // ───────────────── AI Chat ─────────────────
//        composable(
//            route = "ai_chat",
//            exitTransition = {
//                // AI Chat goes OUT to RIGHT
//                slideOutOfContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Right,
//                    animationSpec = tween(1000)
//                )
//            },
//            popEnterTransition = {
//                // AI Chat comes back IN from RIGHT
//                slideIntoContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Right,
//                    animationSpec = tween(1000)
//                )
//            }
//        ) {
//            AIChatScreen(
//                onMenuClick = {
//                    navController.navigate("dashboard")
//                }
//            )
//        }


        composable(
            route = "ai_chat"
        ) {
            AIChatScreen(
                onMenuClick = {
                    navController.navigate("dashboard")
                }
            )
        }



    }
}