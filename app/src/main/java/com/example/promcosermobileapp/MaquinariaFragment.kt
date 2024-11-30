package com.example.promcosermobileapp

import Adapter.MaquinariaAdapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.promcosermobileapp.ui.home.model.Maquinaria
import com.example.promcosermobileapp.ui.maquinaria.model.MaquinariaViewModel


class MaquinariaFragment : Fragment() {
    private val viewModel: MaquinariaViewModel by activityViewModels()

    private lateinit var btnVerMaquinarias: Button
    private lateinit var registrar: Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_maquinaria, container, false)

        // Inicializar el botón
        btnVerMaquinarias = view.findViewById(R.id.btnVerMaquinarias)
        registrar = view.findViewById(R.id.btnRegistroMaquinaria)

        // Configurar el listener del botón
        btnVerMaquinarias.setOnClickListener {
            // Navegar al fragmento de lista de maquinarias
            findNavController().navigate(R.id.action_maquinariaFragment_to_listaMaquinariasFragment)
        }

        registrar.setOnClickListener {

            // Inicialización de vistas
            val modelo_: TextView = view.findViewById(R.id.etRegModelo)
            val marca_: TextView = view.findViewById(R.id.etRegMarca)
            val placa_: TextView = view.findViewById(R.id.etRegPlaca)
            val anioCompra_: TextView = view.findViewById(R.id.etRegAnioCompra)
            val horometroCompra_: TextView = view.findViewById(R.id.etRegHorometroInicio)
            val horometroActual_: TextView = view.findViewById(R.id.etRegistroHorometroActual)

            if (modelo_.text.isEmpty() || marca_.text.isEmpty() || placa_.text.isEmpty() || anioCompra_ == null || horometroCompra_ == null || horometroActual_ == null) {
                Toast.makeText(requireContext(), "Por favor, complete todos los campos correctamente.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val estado_ = when (view.findViewById<RadioGroup>(R.id.rgMaquinariaEstado).checkedRadioButtonId) {
                R.id.rbMaquinariaActiva -> true  // Estado activa como `true`
                R.id.rbMaquinariaInactiva -> false // Estado inactiva como `false`
                else -> {
                    Toast.makeText(requireContext(), "Seleccione un estado.", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }

            // Crear objeto Maquinaria
            val maquinaria = Maquinaria(
                modelo = modelo_.text.toString().trim(),
                placa = placa_.text.toString().trim(),
                marca = marca_.text.toString().trim(),
                anioCompra = anioCompra_.text.toString().trim().toIntOrNull() ?: 0,
                horometroCompra = horometroCompra_.text.toString().trim().toFloatOrNull() ?: 0.0f,
                horometroActual = horometroActual_.text.toString().trim().toFloatOrNull() ?: 0.0f,
                estado = estado_
            )

            // Mostrar un mensaje indicando que se guardará
            Toast.makeText(requireContext(), "Registrando maquinaria...", Toast.LENGTH_SHORT).show()


            viewModel.createMaquinaria(maquinaria)

            // Mostrar mensaje de éxito
            Toast.makeText(requireContext(), "Maquinaria registrada exitosamente", Toast.LENGTH_LONG).show()

        }

        return view
    }
}
