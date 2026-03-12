package edu.ucne.luis_gabriel_ap2_p2.presentation.list

sealed interface LisJugadorUiEvent {
    data class UpdateFilter(val nombres: String) : LisJugadorUiEvent
    data object Search : LisJugadorUiEvent
}