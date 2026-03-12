package edu.ucne.luis_gabriel_ap2_p2.presentation.detail

data class DetailJugadorUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val jugadorId: Int = 0,
    val nombres: String = "",
    val email: String = "",
    val nombresError: String? = null,
    val emailError: String? = null
)