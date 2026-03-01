package com.docelar.padaria.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.docelar.padaria.R
import com.docelar.padaria.data.AppDatabase
import com.docelar.padaria.databinding.ActivityPedidosBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PedidosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPedidosBinding
    private lateinit var adapter: PedidoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPedidosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.titulo_pedidos)

        adapter = PedidoAdapter(emptyList()) { pedido ->
            lifecycleScope.launch {
                AppDatabase.getInstance(this@PedidosActivity).pedidoDao().marcarConcluido(pedido.id)
            }
        }
        binding.recyclerPedidos.layoutManager = LinearLayoutManager(this)
        binding.recyclerPedidos.adapter = adapter

        val db = AppDatabase.getInstance(this)
        lifecycleScope.launch {
            db.pedidoDao().todos().collectLatest { list ->
                adapter.update(list)
                binding.emptyPedidos.visibility = if (list.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
            }
        }

        binding.fabNovoPedido.setOnClickListener {
            startActivity(Intent(this, NovoPedidoActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
