package com.example.promcosermobileapp

import Adapter.MaquinariaAdapter
import Clases.Maquinaria
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MaquinariaFragment : Fragment() {

    private lateinit var btnVerMaquinarias: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_maquinaria, container, false)

        // Inicializar el botón
        btnVerMaquinarias = view.findViewById(R.id.btnVerMaquinarias)

        // Configurar el listener del botón
        btnVerMaquinarias.setOnClickListener {
            // Navegar al fragmento de lista de maquinarias
            findNavController().navigate(R.id.action_maquinariaFragment_to_listaMaquinariasFragment)
        }

        return view
    }
}
