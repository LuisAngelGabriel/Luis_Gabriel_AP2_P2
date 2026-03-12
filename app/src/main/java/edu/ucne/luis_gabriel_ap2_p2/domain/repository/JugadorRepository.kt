package edu.ucne.luis_gabriel_ap2_p2.domain.repository

import edu.ucne.luis_gabriel_ap2_p2.data.remote.Resource
import edu.ucne.luis_gabriel_ap2_p2.domain.model.Jugador
import kotlinx.coroutines.flow.Flow

interface JugadorRepository {
    fun getJugadores(): Flow<Resource<List<Jugador>>>
    fun getJugador(id: Int): Flow<Resource<Jugador>>
    fun saveJugador(jugador: Jugador): Flow<Resource<Jugador>>
    fun updateJugador(id: Int, jugador: Jugador): Flow<Resource<Unit>>
}