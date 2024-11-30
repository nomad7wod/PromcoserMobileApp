package com.example.promcosermobileapp.ui.cliente.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promcosermobileapp.R
import com.example.promcosermobileapp.ui.cliente.model.ClienteModel

class ClienteAdapter(
    private var lstclientes: List<ClienteModel>
) : RecyclerView.Adapter<ClienteAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombreCliente = itemView.findViewById<TextView>(R.id.tvNombres)
        val tvApellidoCliente = itemView.findViewById<TextView>(R.id.tvApellidos)
        val tvDniCliente = itemView.findViewById<TextView>(R.id.tvDNI)
        val tvRUC = itemView.findViewById<TextView>(R.id.tvRUC)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_cliente, parent, false))
    }

    override fun getItemCount(): Int = lstclientes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemCliente = lstclientes[position]
        // Configurar los elementos de la vista con los datos del cliente
        holder.tvNombreCliente.text = itemCliente.nombre
        holder.tvApellidoCliente.text = itemCliente.apellido
        holder.tvDniCliente.text = itemCliente.dni
        holder.tvRUC.text = itemCliente.ruc

    }

    fun updateCliente(newClienteList: List<ClienteModel>) {
        lstclientes = newClienteList
        notifyDataSetChanged()
    }


}