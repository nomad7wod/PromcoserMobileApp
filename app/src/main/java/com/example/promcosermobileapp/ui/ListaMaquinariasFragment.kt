package com.example.promcosermobileapp.ui

import Adapter.MaquinariaAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promcosermobileapp.R
import com.example.promcosermobileapp.ui.home.model.Maquinaria

class ListaMaquinariasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_lista_maquinarias_fragment, container, false)

        // Configurar el RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewMaquinarias)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MaquinariaAdapter(obtenerListaDeMaquinarias())

        return view
    }

    // Función para obtener la lista de maquinarias (deberías implementarla según tu lógica)
    private fun obtenerListaDeMaquinarias(): List<Maquinaria> {
        // Retorna la lista de maquinarias desde tu base de datos o fuente de datos
        return listOf()
    }
}
