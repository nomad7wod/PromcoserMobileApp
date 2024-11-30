package com.example.promcosermobileapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.promcosermobileapp.R
import com.example.promcosermobileapp.databinding.FragmentHomeBinding
import com.example.promcosermobileapp.ui.home.model.ParteDiarioModel
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var selectedDate: Calendar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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

        // Observa el resultado de la creación del parte diario
        viewModel.parteDiarioList.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(context, "Parte Diario guardado exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error al guardar el Parte Diario", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupSpinners() {
        // Setup Cliente Spinner
        viewModel.clientes.observe(viewLifecycleOwner) { clientes ->
            if (clientes.isNotEmpty()) {
                val clienteNames = clientes.map { it.nombre ?: "Cliente sin nombre" }
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    clienteNames
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerCliente.adapter = adapter
            } else {
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
            } else {
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
            } else {
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
    private fun formatToISO8601(calendar: Calendar): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC") // Establece UTC como la zona horaria
        return sdf.format(calendar.time)
    }

    private fun setupButton() {
        binding.btnGuardar.setOnClickListener {
            val horometroInicio = binding.etHorometroInicio.text.toString()
            val horometroFinal = binding.etHorometroFinal.text.toString()
            val fecha = selectedDate?.let {
                formatToISO8601(it) // Convierte a formato ISO 8601
            } ?: "Fecha no seleccionada"

            val fechamantenimiento = selectedDate?.let {
                formatToISO8601(it) // Convierte a formato ISO 8601
            } ?: "Fecha no seleccionada"

            // Validaciones
            if (horometroInicio.isBlank() || horometroFinal.isBlank() || fecha == "Fecha no seleccionada") {
                if (horometroInicio.isBlank()) binding.etHorometroInicio.error = "Campo obligatorio"
                if (horometroFinal.isBlank()) binding.etHorometroFinal.error = "Campo obligatorio"
                if (fecha == "Fecha no seleccionada") {
                    Toast.makeText(context, "Debe seleccionar una fecha", Toast.LENGTH_SHORT).show()
                }
                return@setOnClickListener
            }

            // Obtener datos seleccionados
            val idCliente = binding.spinnerCliente.selectedItemPosition // Asumiendo que el ID es el índice
            val idPersonal = binding.spinnerOperador.selectedItemPosition // Lo mismo para el personal
            val idMaquinaria = binding.spinnerPlaca.selectedItemPosition // Lo mismo para maquinaria

            // Crear el objeto ParteDiario
            val parteDiario = ParteDiarioModel(
                fecha = fecha,
                firmas = true, // Suponiendo que el parte diario tiene una firma
                horometroInicio = horometroInicio.toDouble(),
                horometroFinal = horometroFinal.toDouble(),
                idCliente = binding.spinnerCliente.selectedItemPosition + 1,
                idPersonal = binding.spinnerOperador.selectedItemPosition + 1,
                idMaquinaria = binding.spinnerPlaca.selectedItemPosition + 1,
                lugarTrabajo = binding.etLugarTrabajo.text.toString(),
                petroleo = binding.etPetroleo.text.toString().toDouble(),
                aceite = binding.etAceiteHidraulico.text.toString().toDouble(),
                proximoMantenimiento = fechamantenimiento
            )
            val jsonParteDiario = Gson().toJson(parteDiario)
            Log.d("ParteDiarioJSON", jsonParteDiario)
            Log.d("ParteDiario", parteDiario.toString())

            // Llamar al ViewModel para guardar el Parte Diario
            viewModel.createParteDiario(parteDiario)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}