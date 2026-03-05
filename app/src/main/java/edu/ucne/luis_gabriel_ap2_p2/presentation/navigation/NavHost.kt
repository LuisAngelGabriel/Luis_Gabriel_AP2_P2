package edu.ucne.luis_gabriel_ap2_p2.presentation.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.luis_gabriel_ap2_p2.presentation.list.ListBorrameScreen
import edu.ucne.luis_gabriel_ap2_p2.presentation.detail.DetailBorrameScreen

@Composable
fun NavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.ListBorrame
    ) {
        composable<Screen.ListBorrame> {
            ListBorrameScreen(
                onNavigateToDetail = {
                    navHostController.navigate(Screen.DetailBorrame(id = 0))
                }
            )
        }

        composable<Screen.DetailBorrame> {
            DetailBorrameScreen(
                onBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}