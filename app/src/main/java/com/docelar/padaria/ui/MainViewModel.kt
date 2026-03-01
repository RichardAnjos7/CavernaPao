package com.docelar.padaria.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.docelar.padaria.data.AppDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class DashboardState(
    val totalVendas: Double = 0.0,
    val pedidosPendentes: Int = 0,
    val itensEmAlerta: Int = 0
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)

    val dashboardState: StateFlow<DashboardState> = combine(
        db.vendaDao().totalVendas(),
        db.pedidoDao().quantidadePendentes(),
        db.estoqueDao().quantidadeAlertas()
    ) { total, pendentes, alertas ->
        DashboardState(
            totalVendas = total ?: 0.0,
            pedidosPendentes = pendentes,
            itensEmAlerta = alertas
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DashboardState()
    )
}
