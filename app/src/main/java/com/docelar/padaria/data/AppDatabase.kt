package com.docelar.padaria.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Venda::class, Pedido::class, ItemEstoque::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vendaDao(): VendaDao
    abstract fun pedidoDao(): PedidoDao
    abstract fun estoqueDao(): EstoqueDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "docelar_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
