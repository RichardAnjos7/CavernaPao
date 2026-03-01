package com.docelar.padaria.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estoque")
data class ItemEstoque(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String,
    val quantidade: Int,
    val unidade: String = "un",
    val quantidadeMinima: Int = 5
) {
    val estaAbaixoDoMinimo: Boolean get() = quantidade <= quantidadeMinima
}
