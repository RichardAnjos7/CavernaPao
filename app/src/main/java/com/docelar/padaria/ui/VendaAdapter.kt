package com.docelar.padaria.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.docelar.padaria.data.Venda
import com.docelar.padaria.databinding.ItemVendaBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class VendaAdapter(private var items: List<Venda>) : RecyclerView.Adapter<VendaAdapter.ViewHolder>() {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    fun update(newItems: List<Venda>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVendaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemVendaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(venda: Venda) {
            binding.itemVendaDescricao.text = venda.descricao
            binding.itemVendaDetalhe.text = "${venda.quantidade} x ${numberFormat.format(venda.valorUnitario)} • ${dateFormat.format(Date(venda.dataHora))}"
            binding.itemVendaTotal.text = "Total: ${numberFormat.format(venda.total)}"
        }
    }
}
