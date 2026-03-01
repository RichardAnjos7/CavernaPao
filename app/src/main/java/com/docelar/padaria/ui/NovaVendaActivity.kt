package com.docelar.padaria.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.docelar.padaria.R
import com.docelar.padaria.data.AppDatabase
import com.docelar.padaria.data.Venda
import com.docelar.padaria.databinding.ActivityNovaVendaBinding
import com.docelar.padaria.util.AccentInputFilter
import kotlinx.coroutines.launch

class NovaVendaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNovaVendaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNovaVendaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.nova_venda)

        AccentInputFilter.install(binding.inputDescricao)
        binding.btnSalvarVenda.setOnClickListener { salvar() }
    }

    private fun salvar() {
        val descricao = binding.inputDescricao.text?.toString()?.trim()
        val qtdStr = binding.inputQuantidade.text?.toString()?.trim()
        val valorStr = binding.inputValor.text?.toString()?.trim()?.replace(",", ".")

        if (descricao.isNullOrEmpty()) {
            binding.inputDescricao.error = "Informe a descrição"
            return
        }
        val quantidade = qtdStr?.toIntOrNull() ?: 0
        if (quantidade <= 0) {
            binding.inputQuantidade.error = "Informe a quantidade"
            return
        }
        val valor = valorStr?.toDoubleOrNull() ?: 0.0
        if (valor <= 0) {
            binding.inputValor.error = "Informe o valor"
            return
        }

        lifecycleScope.launch {
            AppDatabase.getInstance(this@NovaVendaActivity).vendaDao()
                .inserir(Venda(descricao = descricao, quantidade = quantidade, valorUnitario = valor))
            Toast.makeText(this@NovaVendaActivity, "Venda registrada!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
