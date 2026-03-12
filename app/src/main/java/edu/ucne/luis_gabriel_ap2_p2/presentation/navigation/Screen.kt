package edu.ucne.luis_gabriel_ap2_p2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object ListJugador : Screen()

    @Serializable
    data class DetailJugador(val id: Int) : Screen()
}