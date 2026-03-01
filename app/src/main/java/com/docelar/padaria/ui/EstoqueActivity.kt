package com.docelar.padaria.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.docelar.padaria.R
import com.docelar.padaria.data.AppDatabase
import com.docelar.padaria.data.ItemEstoque
import com.docelar.padaria.databinding.ActivityEstoqueBinding
import com.docelar.padaria.util.AccentInputFilter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EstoqueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEstoqueBinding
    private lateinit var adapter: EstoqueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstoqueBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.titulo_estoque)

        adapter = EstoqueAdapter(emptyList())
        binding.recyclerEstoque.layoutManager = LinearLayoutManager(this)
        binding.recyclerEstoque.adapter = adapter

        val db = AppDatabase.getInstance(this)
        lifecycleScope.launch {
            db.estoqueDao().todos().collectLatest { list ->
                adapter.update(list)
                binding.emptyEstoque.visibility = if (list.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
            }
        }

        binding.fabNovoItem.setOnClickListener { mostrarDialogNovoItem() }
    }

    private fun mostrarDialogNovoItem() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_novo_estoque, null)
        val inputNome = dialogView.findViewById<EditText>(R.id.dialog_estoque_nome)
        AccentInputFilter.install(inputNome)
        val inputQtd = dialogView.findViewById<EditText>(R.id.dialog_estoque_quantidade)
        val inputMin = dialogView.findViewById<EditText>(R.id.dialog_estoque_minimo)
        val inputUnidade = dialogView.findViewById<EditText>(R.id.dialog_estoque_unidade)

        AlertDialog.Builder(this)
            .setTitle("Novo item no estoque")
            .setView(dialogView)
            .setPositiveButton("Adicionar") { _, _ ->
                val nome = inputNome.text?.toString()?.trim()
                val qtd = inputQtd.text?.toString()?.toIntOrNull() ?: 0
                val min = inputMin.text?.toString()?.toIntOrNull() ?: 5
                val unidade = inputUnidade.text?.toString()?.trim()?.ifEmpty { "un" } ?: "un"
                if (!nome.isNullOrEmpty()) {
                    lifecycleScope.launch {
                        AppDatabase.getInstance(this@EstoqueActivity).estoqueDao().inserir(
                            ItemEstoque(nome = nome, quantidade = qtd, quantidadeMinima = min, unidade = unidade)
                        )
                        Toast.makeText(this@EstoqueActivity, "Item adicionado!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
