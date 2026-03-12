package edu.ucne.luis_gabriel_ap2_p2.domain.usecases

import edu.ucne.luis_gabriel_ap2_p2.data.remote.Resource
import edu.ucne.luis_gabriel_ap2_p2.domain.model.Jugador
import edu.ucne.luis_gabriel_ap2_p2.domain.repository.JugadorRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    operator fun invoke(id: Int): Flow<Resource<Jugador>> {
        return repository.getJugador(id)
    }
}