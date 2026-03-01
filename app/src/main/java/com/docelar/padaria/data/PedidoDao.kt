package com.docelar.padaria.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PedidoDao {
    @Query("SELECT * FROM pedidos ORDER BY dataEntrega ASC")
    fun todos(): Flow<List<Pedido>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(pedido: Pedido): Long

    @Update
    suspend fun atualizar(pedido: Pedido)

    @Query("UPDATE pedidos SET concluido = 1 WHERE id = :id")
    suspend fun marcarConcluido(id: Long)

    @Query("SELECT COUNT(*) FROM pedidos WHERE concluido = 0")
    fun quantidadePendentes(): Flow<Int>
}
