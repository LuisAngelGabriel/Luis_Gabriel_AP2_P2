package edu.ucne.luis_gabriel_ap2_p2.data.repository

import edu.ucne.luis_gabriel_ap2_p2.data.remote.Resource
import edu.ucne.luis_gabriel_ap2_p2.data.remotedatasource.JugadorRemoteDataSource
import edu.ucne.luis_gabriel_ap2_p2.domain.model.Jugador
import edu.ucne.luis_gabriel_ap2_p2.domain.model.JugadorRequest
import edu.ucne.luis_gabriel_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JugadorRepositoryImpl @Inject constructor(
    private val remoteDataSource: JugadorRemoteDataSource
) : JugadorRepository {

    override fun getJugadores(): Flow<Resource<List<Jugador>>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getJugadores()
        response.onSuccess { jugadoresDto ->
            emit(Resource.Succes(jugadoresDto.map { it.toDomain() }))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override fun getJugador(id: Int): Flow<Resource<Jugador>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getJugador(id)
        response.onSuccess { jugadorDto ->
            emit(Resource.Succes(jugadorDto.toDomain()))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override fun saveJugador(jugador: Jugador): Flow<Resource<Jugador>> = flow {
        emit(Resource.Loading())
        val request = JugadorRequest(
            nombres = jugador.nombres,
            email = jugador.email
        )
        val response = remoteDataSource.saveJugador(request)
        response.onSuccess { jugadorDto ->
            emit(Resource.Succes(jugadorDto.toDomain()))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error al guardar"))
        }
    }

    override fun updateJugador(id: Int, jugador: Jugador): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        val request = JugadorRequest(
            nombres = jugador.nombres,
            email = jugador.email
        )
        val response = remoteDataSource.updateJugador(id, request)
        response.onSuccess {
            emit(Resource.Succes(Unit))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error al actualizar"))
        }
    }
}