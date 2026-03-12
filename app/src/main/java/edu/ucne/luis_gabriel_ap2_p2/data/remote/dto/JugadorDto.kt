package edu.ucne.luis_gabriel_ap2_p2.data.remote.dto

import edu.ucne.luis_gabriel_ap2_p2.domain.model.Jugador

data class JugadorDto (
    val jugadorId: Int,
    val nombres: String,
    val email: String,
){
    fun toDomain() = Jugador(
        jugadorId = jugadorId,
        nombres = nombres,
        email = email
    )
}