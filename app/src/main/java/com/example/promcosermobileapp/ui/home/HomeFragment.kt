package com.example.promcosermobileapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.promcosermobileapp.databinding.FragmentHomeBinding
import java.util.Calendar

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var selectedDate: Calendar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpinners()
        setupDatePicker()
        setupButton()
    }

    private fun setupSpinners() {
        // Setup Cliente Spinner
        viewModel.clientes.observe(viewLifecycleOwner) { clientes ->
            if (clientes.isNotEmpty()) {
                val clienteNames = clientes.map { it.razonSocial ?: "Cliente sin nombre" }
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    clienteNames
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerCliente.adapter = adapter
                Log.d("Spinner", "Clientes cargados: $clienteNames")
            } else {
                Log.e("Spinner", "Lista de clientes vacía")
                // Optionally, set a default "empty" adapter
                binding.spinnerCliente.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    listOf("No hay clientes disponibles")
                )
            }
        }
        viewModel.loadClientes()

        // Setup Personal Spinner
        viewModel.personal.observe(viewLifecycleOwner) { personal ->
            if (personal.isNotEmpty()) {
                val personalNames = personal.map { it.nombre ?: "Personal sin nombre" }
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    personalNames
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerOperador.adapter = adapter
                Log.d("Spinner", "Personal cargados: $personalNames")
            } else {
                Log.e("Spinner", "Lista de personal vacía")
                binding.spinnerOperador.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    listOf("No hay personal disponible")
                )
            }
        }
        viewModel.loadPersonal()

        // Setup Maquinaria Spinner
        viewModel.maquinaria.observe(viewLifecycleOwner) { maquinaria ->
            if (maquinaria.isNotEmpty()) {
                val maquinariaPlacas = maquinaria.map { it.placa ?: "Placa no disponible" }
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    maquinariaPlacas
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerPlaca.adapter = adapter
                Log.d("Spinner", "Maquinaria cargados: $maquinariaPlacas")
            } else {
                Log.e("Spinner", "Lista de maquinaria vacía")
                binding.spinnerPlaca.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    listOf("No hay maquinaria disponible")
                )
            }
        }
        viewModel.loadMaquinaria()
    }

    private fun setupDatePicker() {
        binding.datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            selectedDate = Calendar.getInstance().apply {
                set(year, monthOfYear, dayOfMonth)
            }
        }
    }






    private fun setupButton() {
        binding.btnGuardar.setOnClickListener {
            val horometroInicio = binding.etHorometroInicio.text.toString()
            val horometroFinal = binding.etHorometroFinal.text.toString()
            val fecha = selectedDate?.let {
                "${it.get(Calendar.DAY_OF_MONTH)}/${it.get(Calendar.MONTH) + 1}/${it.get(Calendar.YEAR)}"
            } ?: "Fecha no seleccionada"

            if (horometroInicio.isBlank() || horometroFinal.isBlank()) {
                // Display error messages
                binding.etHorometroInicio.error = "Campo obligatorio"
                binding.etHorometroFinal.error = "Campo obligatorio"
                return@setOnClickListener
            }

            Log.d("HomeFragment", "Horómetro inicio: $horometroInicio, final: $horometroFinal, fecha: $fecha")
            // Handle other logic or send data to ViewModel
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}