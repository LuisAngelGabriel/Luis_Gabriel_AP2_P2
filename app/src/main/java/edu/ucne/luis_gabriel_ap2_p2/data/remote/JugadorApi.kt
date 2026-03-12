package edu.ucne.luis_gabriel_ap2_p2.data.remote

import edu.ucne.luis_gabriel_ap2_p2.data.remote.dto.JugadorDto
import edu.ucne.luis_gabriel_ap2_p2.domain.model.JugadorRequest
import retrofit2.Response
import retrofit2.http.*

interface JugadorApi {

    @GET("api/Jugadores")
    suspend fun getJugadores(): List<JugadorDto>

    @GET("api/Jugadores/{id}")
    suspend fun getJugador(@Path("id") id: Int): JugadorDto

    @POST("api/Jugadores")
    suspend fun saveJugador(@Body request: JugadorRequest): JugadorDto

    @PUT("api/Jugadores/{id}")
    suspend fun updateJugador(
        @Path("id") id: Int,
        @Body request: JugadorRequest
    ): Response<Unit>

}