package com.example.promcosermobileapp.ui.personal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promcosermobileapp.R
import com.example.promcosermobileapp.ui.personal.model.PersonalModel
import com.example.promcosermobileapp.ui.personal.model.rolmodel

class PersonalAdapter(
    private var lstPersonal: List<PersonalModel>,
    private var lstRoles: List<rolmodel> // Lista de roles
) : RecyclerView.Adapter<PersonalAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDNIPersonal = itemView.findViewById<TextView>(R.id.tvDNIPersonal)
        val tvApellidosPersonal = itemView.findViewById<TextView>(R.id.tvApellidosPersonal)
        val tvNombrePersonal = itemView.findViewById<TextView>(R.id.tvNombrePersonal)
        val tvRol = itemView.findViewById<TextView>(R.id.tvRol)
        val tvEstadoPersonal = itemView.findViewById<TextView>(R.id.tvEstadoPersonal)
        val tvFechaPersonal = itemView.findViewById<TextView>(R.id.tvFechaPersonal)
        val tvNumeroPersonal = itemView.findViewById<TextView>(R.id.tvNumeroPersonal)
        val tvEmailPersonal = itemView.findViewById<TextView>(R.id.tvEmailPersonal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_personal, parent, false))
    }

    override fun getItemCount(): Int = lstPersonal.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemPersonal = lstPersonal[position]

        holder.tvNombrePersonal.text = itemPersonal.nombre
        holder.tvApellidosPersonal.text = itemPersonal.apellido
        holder.tvDNIPersonal.text = itemPersonal.dni
        holder.tvRol.text = getRolName(itemPersonal.idRol)
        holder.tvEstadoPersonal.text = if (itemPersonal.Estado) "Activo" else "Inactivo"
        holder.tvFechaPersonal.text = itemPersonal.fechNacimiento.toString()
        holder.tvNumeroPersonal.text = itemPersonal.telefono
        holder.tvEmailPersonal.text = itemPersonal.correo
    }

    fun updatePersonal(newPersonalList: List<PersonalModel>) {
        lstPersonal = newPersonalList
        notifyDataSetChanged()
    }

    fun updateRoles(newRolesList: List<rolmodel>) {
        lstRoles = newRolesList
        notifyDataSetChanged()
    }

    // Método para obtener el nombre del rol a partir del ID de rol
    private fun getRolName(rolId: Int): String {
        val rol = lstRoles.find { it.idRol == rolId }
        return rol?.descripcion ?: "Desconocido"  // Si no encuentra el rol, devuelve Desconocido
    }
}

