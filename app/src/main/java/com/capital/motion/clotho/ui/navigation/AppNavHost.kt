package com.capital.motion.clotho.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.capital.motion.clotho.ui.screens.AstrologyDetailScreen
import com.capital.motion.clotho.ui.screens.AstrologyViewModel
import com.capital.motion.clotho.ui.screens.DashboardScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    // Single shared ViewModel — survives navigation between dashboard and detail
    val astrologyViewModel: AstrologyViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {

        // ── Dashboard ─────────────────────────────────────────────────────────
        composable("dashboard") {
            DashboardScreen(
                navController = navController,
                viewModel = astrologyViewModel
            )
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
    }
}