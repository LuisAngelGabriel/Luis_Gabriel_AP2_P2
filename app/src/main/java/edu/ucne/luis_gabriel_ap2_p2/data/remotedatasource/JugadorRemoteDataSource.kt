package edu.ucne.luis_gabriel_ap2_p2.data.remotedatasource

import retrofit2.HttpException
import edu.ucne.luis_gabriel_ap2_p2.data.remote.JugadorApi
import edu.ucne.luis_gabriel_ap2_p2.data.remote.dto.JugadorDto
import edu.ucne.luis_gabriel_ap2_p2.domain.model.JugadorRequest
import javax.inject.Inject

class JugadorRemoteDataSource @Inject constructor(
    private val api: JugadorApi
) {
    suspend fun getJugadores(): Result<List<JugadorDto>> {
        return try {
            val response = api.getJugadores()
            Result.success(response)
        } catch (e: HttpException) {
            val errorMsg = e.response()?.errorBody()?.string() ?: "Error de servidor"
            Result.failure(Exception(errorMsg))
        } catch (e: Exception) {
            Result.failure(Exception(e.localizedMessage ?: "Error desconocido"))
        }
    }

    suspend fun getJugador(id: Int): Result<JugadorDto> {
        return try {
            val response = api.getJugador(id)
            Result.success(response)
        } catch (e: HttpException) {
            val errorMsg = e.response()?.errorBody()?.string() ?: "Error de servidor"
            Result.failure(Exception(errorMsg))
        } catch (e: Exception) {
            Result.failure(Exception(e.localizedMessage ?: "Error desconocido"))
        }
    }

    suspend fun saveJugador(request: JugadorRequest): Result<JugadorDto> {
        return try {
            val response = api.saveJugador(request)
            Result.success(response)
        } catch (e: HttpException) {
            val errorMsg = e.response()?.errorBody()?.string() ?: "Error de servidor"
            Result.failure(Exception(errorMsg))
        } catch (e: Exception) {
            Result.failure(Exception(e.localizedMessage ?: "Error desconocido"))
        }
    }

    suspend fun updateJugador(id: Int, request: JugadorRequest): Result<Unit> {
        return try {
            val response = api.updateJugador(id, request)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                val errorMsg = response.errorBody()?.string() ?: "Error ${response.code()}"
                Result.failure(Exception(errorMsg))
            }
        } catch (e: HttpException) {
            val errorMsg = e.response()?.errorBody()?.string() ?: "Error de servidor"
            Result.failure(Exception(errorMsg))
        } catch (e: Exception) {
            Result.failure(Exception(e.localizedMessage ?: "Error desconocido"))
        }
    }
}