package com.docelar.padaria.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pedidos")
data class Pedido(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cliente: String,
    val descricao: String,
    val valor: Double,
    val dataEntrega: Long,
    val concluido: Boolean = false,
    val dataCriacao: Long = System.currentTimeMillis()
)
