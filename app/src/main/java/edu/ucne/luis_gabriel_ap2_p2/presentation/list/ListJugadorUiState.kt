package edu.ucne.luis_gabriel_ap2_p2.presentation.list

import edu.ucne.luis_gabriel_ap2_p2.domain.model.Jugador

data class ListJugadorUiState (
    val isLoading: Boolean = false,
    val jugadores: List<Jugador> = emptyList(),
    val error: String? = null,
)