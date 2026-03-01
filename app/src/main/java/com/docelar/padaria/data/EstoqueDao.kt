package com.docelar.padaria.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EstoqueDao {
    @Query("SELECT * FROM estoque ORDER BY nome")
    fun todos(): Flow<List<ItemEstoque>>

    @Query("SELECT * FROM estoque WHERE quantidade <= quantidadeMinima")
    fun alertas(): Flow<List<ItemEstoque>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(item: ItemEstoque): Long

    @Update
    suspend fun atualizar(item: ItemEstoque)

    @Delete
    suspend fun remover(item: ItemEstoque)
}
