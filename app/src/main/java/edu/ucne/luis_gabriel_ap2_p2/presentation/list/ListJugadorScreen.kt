package edu.ucne.luis_gabriel_ap2_p2.presentation.jugador_list
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.luis_gabriel_ap2_p2.presentation.list.LisJugadorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListJugadorScreen(
    viewModel: LisJugadorViewModel = hiltViewModel(),
    onAddJugador: () -> Unit,
    onEditJugador: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lista de Jugadores") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddJugador) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Jugador")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            if (state.error != null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = state.error!!, color = MaterialTheme.colorScheme.error)
                }
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.jugadores) { jugador ->
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onEditJugador(jugador.jugadorId) }
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "ID: ${jugador.jugadorId}",
                                style = MaterialTheme.typography.labelSmall
                            )
                            Text(
                                text = "Nombre: ${jugador.nombres}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Email: ${jugador.email}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            Text(
                text = "Total de jugadores registrados: ${state.jugadores.size}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}