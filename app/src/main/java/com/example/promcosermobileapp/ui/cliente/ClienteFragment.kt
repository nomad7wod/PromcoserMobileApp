package com.example.promcosermobileapp.ui.cliente

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.promcosermobileapp.R
import com.example.promcosermobileapp.ui.cliente.model.ClienteModel
import com.google.gson.Gson

class ClienteFragment : Fragment() {

    private val viewModel: ClienteViewModel by activityViewModels()

    private fun limpiarFormulario() {
        requireView().findViewById<TextView>(R.id.etNombreCliente).text = ""
        requireView().findViewById<TextView>(R.id.etApellidoCliente).text = ""
        requireView().findViewById<TextView>(R.id.etDniCliente).text = ""
        requireView().findViewById<TextView>(R.id.etRazonSocial).text = ""
        requireView().findViewById<TextView>(R.id.etRuc).text = ""
        requireView().findViewById<TextView>(R.id.etTelefonoCliente).text = ""
        requireView().findViewById<TextView>(R.id.etCorreoCliente).text = ""
        requireView().findViewById<TextView>(R.id.etDireccionCliente).text = ""

        requireView().findViewById<Spinner>(R.id.spTipoCliente).setSelection(0)

        val radioGroup = requireView().findViewById<RadioGroup>(R.id.rgEstadoCliente)
        radioGroup.clearCheck()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_cliente, container, false)

        val btnhist: Button = view.findViewById(R.id.btnhistorialCliente)
        val navController = findNavController()
        btnhist.setOnClickListener {
            navController.navigate(R.id.historialClienteFragment)
        }

        viewModel.clienteList.observe(viewLifecycleOwner) { isSuccessful ->
            if (isSuccessful) {
                Toast.makeText(requireContext(), "Registro exitoso", Toast.LENGTH_SHORT).show()
                limpiarFormulario()
                Log.d("ClienteFragment", "Cliente registrado exitosamente")
            } else {
                Toast.makeText(requireContext(), "Error al registrar el cliente", Toast.LENGTH_SHORT).show()
                Log.e("ClienteFragment", "Error al registrar cliente")
            }
        }

        val tipoCliente: Spinner = view.findViewById(R.id.spTipoCliente)
        val nombre: TextView = view.findViewById(R.id.etNombreCliente)
        val apellido: TextView = view.findViewById(R.id.etApellidoCliente)
        val dni: TextView = view.findViewById(R.id.etDniCliente)
        val razonSocial: TextView = view.findViewById(R.id.etRazonSocial)
        val ruc: TextView = view.findViewById(R.id.etRuc)
        val telefono: TextView = view.findViewById(R.id.etTelefonoCliente)
        val correo: TextView = view.findViewById(R.id.etCorreoCliente)
        val direccion: TextView = view.findViewById(R.id.etDireccionCliente)
        val regEstado: RadioGroup = view.findViewById(R.id.rgEstadoCliente)

        val opciones = listOf("Seleccione tipo", "Cliente Natural", "Cliente Jurídico")
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            opciones
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tipoCliente.adapter = adapter

        val btnRegistrarCliente: Button = view.findViewById(R.id.btnRegistrarCliente)
        btnRegistrarCliente.setOnClickListener {
            val tipoClienteSeleccionado = when (tipoCliente.selectedItem?.toString()) {
                "Cliente Natural" -> "natural"
                "Cliente Jurídico" -> "juridico"
                else -> ""
            }

            val nombreText = nombre.text.toString()
            val apellidoText = apellido.text.toString()
            val dniText = dni.text.toString()
            val razonSocialText = razonSocial.text.toString()
            val rucText = ruc.text.toString()
            val telefonoText = telefono.text.toString()
            val correoText = correo.text.toString()
            val direccionText = direccion.text.toString()
            val estadoSeleccionadoId = regEstado.checkedRadioButtonId
            val estadoRadioButton: RadioButton = view.findViewById(estadoSeleccionadoId)
            val estadoBoolean = estadoRadioButton.text.toString() == "Activo"

            if (tipoClienteSeleccionado.isEmpty() || tipoClienteSeleccionado == "Seleccione tipo") {
                Log.e("Error", "Debe seleccionar un tipo de cliente válido.")
                return@setOnClickListener
            }

            val cliente = ClienteModel(
                tipoCliente = tipoClienteSeleccionado,
                nombre = nombreText,
                apellido = apellidoText,
                dni = dniText,
                razonSocial = razonSocialText,
                ruc = rucText,
                telefono = telefonoText,
                correo = correoText,
                direccion = direccionText,
                estado = estadoBoolean
            )

            Log.d("DatosEnviados", Gson().toJson(cliente))

            viewModel.createCliente(cliente)
        }

        return view
    }
}


