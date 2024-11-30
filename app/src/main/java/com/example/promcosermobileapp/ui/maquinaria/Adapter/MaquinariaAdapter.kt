package Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promcosermobileapp.R
import com.example.promcosermobileapp.ui.home.model.Maquinaria

class MaquinariaAdapter(
    private var maquinariaList: List<Maquinaria>
) : RecyclerView.Adapter<MaquinariaAdapter.ViewHolder>() {

    // ViewHolder para manejar los elementos de la lista
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvModeloMarca: TextView = itemView.findViewById(R.id.tvModeloMarca)
        val tvPlaca: TextView = itemView.findViewById(R.id.tvPlaca)
        val tvAnioCompra: TextView = itemView.findViewById(R.id.tvAnioCompra)
        val tvHorometroCompra: TextView = itemView.findViewById(R.id.tvHorometroCompra)
        val tvHorometroActual: TextView = itemView.findViewById(R.id.tvHorometroActual)
        val tvEstado: TextView = itemView.findViewById(R.id.tvEstado)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_maquinaria, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = maquinariaList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val maquinaria = maquinariaList[position]

        // Asignar valores a los elementos del ViewHolder
        holder.tvModeloMarca.text = "${maquinaria.modelo} - ${maquinaria.marca}"
        holder.tvPlaca.text = "Placa: ${maquinaria.placa}"
        holder.tvAnioCompra.text = "Año de Compra: ${maquinaria.anioCompra}"
        holder.tvHorometroCompra.text = "Horómetro (Compra): ${maquinaria.horometroCompra}"
        holder.tvHorometroActual.text = "Horómetro (Actual): ${maquinaria.horometroActual}"
        holder.tvEstado.text = if (maquinaria.estado) "Activa" else "Inactiva"
    }

    // Método para actualizar la lista de maquinarias
    fun updateMaquinariaList(newList: List<Maquinaria>) {
        maquinariaList = newList
        notifyDataSetChanged()
    }
}
