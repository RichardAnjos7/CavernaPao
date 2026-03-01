package com.docelar.padaria.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.docelar.padaria.data.Pedido
import com.docelar.padaria.databinding.ItemPedidoBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class PedidoAdapter(
    private var items: List<Pedido>,
    private val onConcluirClick: (Pedido) -> Unit
) : RecyclerView.Adapter<PedidoAdapter.ViewHolder>() {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    fun update(newItems: List<Pedido>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPedidoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemPedidoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pedido: Pedido) {
            binding.itemPedidoCliente.text = pedido.cliente
            binding.itemPedidoDescricao.text = pedido.descricao
            binding.itemPedidoData.text = "Entrega: ${dateFormat.format(Date(pedido.dataEntrega))}"
            binding.itemPedidoValor.text = numberFormat.format(pedido.valor)
            binding.chipConcluido.visibility = if (pedido.concluido) View.VISIBLE else View.GONE
            if (!pedido.concluido) {
                binding.root.setOnClickListener { onConcluirClick(pedido) }
            }
        }
    }
}
