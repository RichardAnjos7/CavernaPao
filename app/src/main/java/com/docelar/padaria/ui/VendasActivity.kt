package com.docelar.padaria.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.docelar.padaria.R
import com.docelar.padaria.data.AppDatabase
import com.docelar.padaria.data.Venda
import com.docelar.padaria.databinding.ActivityVendasBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class VendasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVendasBinding
    private lateinit var adapter: VendaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVendasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.titulo_vendas)

        adapter = VendaAdapter(emptyList())
        binding.recyclerVendas.layoutManager = LinearLayoutManager(this)
        binding.recyclerVendas.adapter = adapter

        val db = AppDatabase.getInstance(this)
        lifecycleScope.launch {
            db.vendaDao().todas().collectLatest { list ->
                adapter.update(list)
                binding.emptyVendas.visibility = if (list.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
            }
        }

        binding.fabNovaVenda.setOnClickListener {
            startActivity(Intent(this, NovaVendaActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
