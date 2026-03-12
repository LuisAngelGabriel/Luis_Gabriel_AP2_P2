package edu.ucne.luis_gabriel_ap2_p2.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.luis_gabriel_ap2_p2.data.remote.Resource
import edu.ucne.luis_gabriel_ap2_p2.domain.model.Jugador
import edu.ucne.luis_gabriel_ap2_p2.domain.usecases.GetJugadorUseCase
import edu.ucne.luis_gabriel_ap2_p2.domain.usecases.SaveJugadorUseCase
import edu.ucne.luis_gabriel_ap2_p2.domain.usecases.UpdateJugadorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailJugadoViewModel @Inject constructor(
    private val getJugadorUseCase: GetJugadorUseCase,
    private val saveJugadorUseCase: SaveJugadorUseCase,
    private val updateJugadorUseCase: UpdateJugadorUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailJugadorUiState())
    val state = _state.asStateFlow()

    init {
        val id = savedStateHandle.get<Int>("jugadorId") ?: 0
        if (id > 0) {
            _state.update { it.copy(jugadorId = id) }
            loadJugador(id)
        }
    }

    private fun loadJugador(id: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getJugadorUseCase(id).collect { result ->
                when (result) {
                    is Resource.Succes -> {
                        val jugador = result.data
                        _state.update {
                            it.copy(
                                isLoading = false,
                                nombres = jugador?.nombres ?: "",
                                email = jugador?.email ?: ""
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false, error = result.message) }
                    }
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    fun onNombresChange(valor: String) {
        _state.update { it.copy(nombres = valor, nombresError = null) }
    }

    fun onEmailChange(valor: String) {
        _state.update { it.copy(email = valor, emailError = null) }
    }

    fun save() {
        if (!validar()) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val current = _state.value
            val jugador = Jugador(
                jugadorId = current.jugadorId,
                nombres = current.nombres,
                email = current.email
            )

            val flow = if (current.jugadorId > 0)
                updateJugadorUseCase(current.jugadorId, jugador)
            else
                saveJugadorUseCase(jugador)

            flow.collect { result ->
                when (result) {
                    is Resource.Succes -> {
                        _state.update { it.copy(isLoading = false, isSuccess = true) }
                    }
                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false, error = result.message) }
                    }
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    private fun validar(): Boolean {
        var esValido = true
        if (_state.value.nombres.isBlank()) {
            _state.update { it.copy(nombresError = "El nombre es obligatorio") }
            esValido = false
        }
        if (_state.value.email.isBlank() || !(_state.value.email.contains("@"))) {
            _state.update { it.copy(emailError = "Email inválido (debe contener @)") }
            esValido = false
        }
        return esValido
    }
}