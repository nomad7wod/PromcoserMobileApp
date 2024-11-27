package com.example.promcosermobileapp.ui.personal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promcosermobileapp.R
import com.example.promcosermobileapp.ui.personal.model.PersonalModel

class PersonalAdapter(private var lstPersonal: List<PersonalModel>) :
    RecyclerView.Adapter<PersonalAdapter.ViewHolder>() {

    // Clase interna para manejar las vistas de cada ítem
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

    // Crear las vistas para cada ítem
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_personal, parent, false))
    }

    // Obtener el tamaño de la lista
    override fun getItemCount(): Int = lstPersonal.size

    // Asignar valores a los elementos del diseño
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemPersonal = lstPersonal[position]

        holder.tvNombrePersonal.text = itemPersonal.nombre
        holder.tvApellidosPersonal.text = itemPersonal.apellido
        holder.tvDNIPersonal.text = itemPersonal.dni
        holder.tvRol.text = "Rol ID: ${itemPersonal.id_rol}" // Puedes traducir el ID a un nombre si tienes un mapa de roles
        holder.tvEstadoPersonal.text = if (itemPersonal.estado) "Activo" else "Inactivo"
        holder.tvFechaPersonal.text = itemPersonal.fech_nacimiento.toString() // Formatea la fecha si es necesario
        holder.tvNumeroPersonal.text = itemPersonal.telefono
        holder.tvEmailPersonal.text = itemPersonal.correo
    }

    // Método para actualizar la lista de datos en el adaptador
    fun updatePersonal(newPersonalList: List<PersonalModel>) {
        lstPersonal = newPersonalList
        notifyDataSetChanged() // Notifica a RecyclerView que los datos han cambiado
    }
}