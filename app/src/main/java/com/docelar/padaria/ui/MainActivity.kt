package com.docelar.padaria.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
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

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        title = getString(R.string.app_name)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.nav_app_bar_open_drawer_description,
            R.string.nav_app_bar_navigate_up_description
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_vendas -> startActivity(Intent(this, VendasActivity::class.java))
                R.id.nav_pedidos -> startActivity(Intent(this, PedidosActivity::class.java))
                R.id.nav_estoque -> startActivity(Intent(this, EstoqueActivity::class.java))
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        lifecycleScope.launch {
            viewModel.dashboardState.collectLatest { state ->
                binding.dashboardFaturamento.text = currencyFormat.format(state.totalVendas)
                binding.dashboardPedidosPendentes.text = state.pedidosPendentes.toString()
                binding.dashboardItensAlerta.text = state.itensEmAlerta.toString()
            }
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    finish()
                }
            }
        })
    }
}
