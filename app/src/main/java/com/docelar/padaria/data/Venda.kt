package com.docelar.padaria.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vendas")
data class Venda(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val descricao: String,
    val quantidade: Int,
    val valorUnitario: Double,
    val dataHora: Long = System.currentTimeMillis()
) {
    val total: Double get() = quantidade * valorUnitario
}
