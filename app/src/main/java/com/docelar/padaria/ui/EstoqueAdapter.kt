package com.docelar.padaria.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.docelar.padaria.data.ItemEstoque
import com.docelar.padaria.databinding.ItemEstoqueBinding

class EstoqueAdapter(private var items: List<ItemEstoque>) : RecyclerView.Adapter<EstoqueAdapter.ViewHolder>() {

    fun update(newItems: List<ItemEstoque>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEstoqueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemEstoqueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemEstoque) {
            binding.itemEstoqueNome.text = item.nome
            binding.itemEstoqueQtd.text = "Quantidade: ${item.quantidade} ${item.unidade}"
            binding.itemEstoqueMin.text = "Mínimo: ${item.quantidadeMinima} ${item.unidade}"
            binding.chipAlerta.visibility = if (item.estaAbaixoDoMinimo) View.VISIBLE else View.GONE
        }
    }
}
