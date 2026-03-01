package com.docelar.padaria.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VendaDao {
    @Query("SELECT * FROM vendas ORDER BY dataHora DESC")
    fun todas(): Flow<List<Venda>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(venda: Venda): Long

    @Query("SELECT SUM(quantidade * valorUnitario) FROM vendas")
    fun totalVendas(): Flow<Double?>
}
