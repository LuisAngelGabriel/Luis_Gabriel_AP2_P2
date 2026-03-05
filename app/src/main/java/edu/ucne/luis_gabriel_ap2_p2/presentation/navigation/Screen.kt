package edu.ucne.luis_gabriel_ap2_p2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object ListBorrame : Screen()

    @Serializable
    data class DetailBorrame(val id: Int) : Screen()
}