package Adapter

import Clases.Maquinaria
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promcosermobileapp.R

class MaquinariaAdapter(private val maquinariaList: List<Maquinaria>) :
    RecyclerView.Adapter<MaquinariaAdapter.MaquinariaViewHolder>() {

    // Clase interna para el ViewHolder
    inner class MaquinariaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvModeloMarca: TextView = itemView.findViewById(R.id.tvModeloMarca)
        val tvPlaca: TextView = itemView.findViewById(R.id.tvPlaca)
        val tvHorometro: TextView = itemView.findViewById(R.id.tvHorometro)
        val tvAnoCompra: TextView = itemView.findViewById(R.id.tvAnoCompra)
        val tvEstado: TextView = itemView.findViewById(R.id.tvEstado)
        val btnDetalles: Button = itemView.findViewById(R.id.btnDetalles)
        val llDetallesRecientes: LinearLayout = itemView.findViewById(R.id.llDetallesRecientes)
        val tvFechaUso: TextView = itemView.findViewById(R.id.tvFechaUso)
        val tvHorasUso: TextView = itemView.findViewById(R.id.tvHorasUso)
        val tvLugarTrabajo: TextView = itemView.findViewById(R.id.tvLugarTrabajo)
        val tvPetroleoUsado: TextView = itemView.findViewById(R.id.tvPetroleoUsado)
        val tvAceiteHidraulico: TextView = itemView.findViewById(R.id.tvAceiteHidraulico)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaquinariaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_maquinaria, parent, false)
        return MaquinariaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MaquinariaViewHolder, position: Int) {
        val maquinaria = maquinariaList[position]

        holder.tvModeloMarca.text = "${maquinaria.modelo} - ${maquinaria.marca}"
        holder.tvPlaca.text = "Placa: ${maquinaria.placa}"
        holder.tvHorometro.text = "Horómetro: ${maquinaria.horometro} h"
        holder.tvAnoCompra.text = "Año de Compra: ${maquinaria.anoCompra}"
        holder.tvEstado.text = "Estado: ${maquinaria.estado}"

        // Detalles recientes
        maquinaria.fechaUso?.let {
            holder.tvFechaUso.text = "Fecha de Uso: $it"
        }
        maquinaria.horasUso?.let {
            holder.tvHorasUso.text = "Horas de Uso: $it"
        }
        maquinaria.lugarTrabajo?.let {
            holder.tvLugarTrabajo.text = "Lugar de Trabajo: $it"
        }
        maquinaria.petroleoUsado?.let {
            holder.tvPetroleoUsado.text = "Petróleo Usado: $it L"
        }
        maquinaria.aceiteHidraulico?.let {
            holder.tvAceiteHidraulico.text = "Aceite Hidráulico: $it L"
        }

        // Manejo de visibilidad para el botón de detalles
        holder.btnDetalles.setOnClickListener {
            if (holder.llDetallesRecientes.visibility == View.GONE) {
                holder.llDetallesRecientes.visibility = View.VISIBLE
            } else {
                holder.llDetallesRecientes.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return maquinariaList.size
    }
}