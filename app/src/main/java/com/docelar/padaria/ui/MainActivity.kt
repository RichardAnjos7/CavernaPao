package com.docelar.padaria.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.docelar.padaria.R
import com.docelar.padaria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        title = getString(R.string.app_name)

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
