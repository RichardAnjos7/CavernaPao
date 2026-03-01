package com.docelar.padaria.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.docelar.padaria.R
import com.docelar.padaria.data.AppDatabase
import com.docelar.padaria.data.Pedido
import com.docelar.padaria.databinding.ActivityNovoPedidoBinding
import com.docelar.padaria.util.AccentInputFilter
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NovoPedidoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNovoPedidoBinding
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNovoPedidoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.novo_pedido)

        binding.inputDataEntrega.hint = "Ex: " + dateFormat.format(Date())
        binding.inputDataEntregaLayout.error = null
        AccentInputFilter.install(binding.inputCliente)
        AccentInputFilter.install(binding.inputDescricao)
        binding.btnSalvarPedido.setOnClickListener { salvar() }
    }

    private fun salvar() {
        binding.inputDataEntregaLayout.error = null
        val cliente = binding.inputCliente.text?.toString()?.trim()
        val descricao = binding.inputDescricao.text?.toString()?.trim()
        val valorStr = binding.inputValor.text?.toString()?.trim()?.replace(",", ".")
        val dataStr = binding.inputDataEntrega.text?.toString()?.trim()

        if (cliente.isNullOrEmpty()) {
            binding.inputCliente.error = "Informe o cliente"
            return
        }
        if (descricao.isNullOrEmpty()) {
            binding.inputDescricao.error = "Informe a descrição"
            return
        }
        val valor = valorStr?.toDoubleOrNull() ?: 0.0
        if (valor <= 0) {
            binding.inputValor.error = "Informe o valor"
            return
        }
        val dataEntrega = parseData(dataStr)
        if (dataEntrega == null) {
            binding.inputDataEntregaLayout.error = "Informe a data (dd/MM/yyyy)"
            return
        }

        lifecycleScope.launch {
            AppDatabase.getInstance(this@NovoPedidoActivity).pedidoDao()
                .inserir(Pedido(cliente = cliente, descricao = descricao, valor = valor, dataEntrega = dataEntrega))
            Toast.makeText(this@NovoPedidoActivity, "Pedido registrado!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun parseData(str: String?): Long? {
        if (str.isNullOrEmpty()) return null
        return try {
            dateFormat.parse(str)?.time
        } catch (_: Exception) {
            null
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
