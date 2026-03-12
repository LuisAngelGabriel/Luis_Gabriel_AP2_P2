package edu.ucne.luis_gabriel_ap2_p2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.luis_gabriel_ap2_p2.presentation.detail.DetailJugadorScreen
import edu.ucne.luis_gabriel_ap2_p2.presentation.jugador_list.ListJugadorScreen

@Composable
fun NavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.ListJugador
    ) {
        composable<Screen.ListJugador> {
            ListJugadorScreen(
                onAddJugador = {
                    navHostController.navigate(Screen.DetailJugador(id = 0))
                },
                onEditJugador = { id ->
                    navHostController.navigate(Screen.DetailJugador(id = id))
                }
            )
        }

        composable<Screen.DetailJugador> {
            DetailJugadorScreen(
                onNavigateBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}