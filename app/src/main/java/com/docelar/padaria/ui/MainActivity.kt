package com.docelar.padaria.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.docelar.padaria.R
import com.docelar.padaria.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        title = getString(R.string.app_name)

        lifecycleScope.launch {
            viewModel.dashboardState.collectLatest { state ->
                binding.dashboardFaturamento.text = currencyFormat.format(state.totalVendas)
                binding.dashboardPedidosPendentes.text = state.pedidosPendentes.toString()
                binding.dashboardItensAlerta.text = state.itensEmAlerta.toString()
            }
        }

        binding.cardVendas.setOnClickListener {
            startActivity(Intent(this, VendasActivity::class.java))
        }
        binding.cardPedidos.setOnClickListener {
            startActivity(Intent(this, PedidosActivity::class.java))
        }
        binding.cardEstoque.setOnClickListener {
            startActivity(Intent(this, EstoqueActivity::class.java))
        }
    }
}
