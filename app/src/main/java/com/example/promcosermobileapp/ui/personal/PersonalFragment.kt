package com.example.promcosermobileapp.ui.personal

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.promcosermobileapp.R
import com.example.promcosermobileapp.ui.personal.model.PersonalModel
import com.google.gson.Gson
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PersonalFragment : Fragment() {


    private val viewModel: PersonalViewModel by activityViewModels()
    private var selectedDay2: Date? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_personal, container, false)

        val btnhist: Button = view.findViewById(R.id.btnhistorial)
        val navController = findNavController()
        btnhist.setOnClickListener {
            navController.navigate(R.id.personalApiFragment)
        }

        val nombre: TextView = view.findViewById(R.id.etnombres)
        val apellido: TextView = view.findViewById(R.id.etapellidos)
        val rol: Spinner = view.findViewById(R.id.spRol)

        // Cargar roles
        viewModel.roles.observe(viewLifecycleOwner) { roles ->
            if (roles.isNotEmpty()) {
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    roles.map { it.descripcion }
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                rol.adapter = adapter
                Log.d("Spinner", "Roles cargados: ${roles.map { it.descripcion }}")
            } else {
                Log.e("Spinner", "Lista de roles vacía")
            }
        }
        viewModel.loadRoles()

        val vtelefono: TextView = view.findViewById(R.id.etnumtel)
        val emai: TextView = view.findViewById(R.id.etemail)
        val dni: TextView = view.findViewById(R.id.etdni)
        val rgestado: RadioGroup = view.findViewById(R.id.rgestado)
        val fechanac: TextView = view.findViewById(R.id.etfechanac)
        fechanac.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, SelectedYear, selectedMonth, selectedDay ->
                    val CalSel = Calendar.getInstance()
                    CalSel.set(SelectedYear, selectedMonth, selectedDay)
                    selectedDay2 = Date(CalSel.timeInMillis)
                    fechanac.text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDay2!!)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        val Ubigeo: Spinner = view.findViewById(R.id.spLocalidad)
        viewModel.ubigeo.observe(viewLifecycleOwner) { ubigeo ->
            if (ubigeo.isNotEmpty()) {
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    ubigeo.map { "${it.departamento} - ${it.provincia} - ${it.distrito}" }
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                Ubigeo.adapter = adapter
                Log.d("Spinner", "Ubigeos cargados: ${ubigeo.map { it.departamento + it.provincia + it.distrito }}")
            } else {
                Log.e("Spinner", "Lista de ubigeos vacía")
            }
        }
        viewModel.loadUbigeo()

        val usuario: TextView = view.findViewById(R.id.etusuario)
        val contraseña: TextView = view.findViewById(R.id.etcontraseña)
        val btnpersonal: Button = view.findViewById(R.id.btnRegistrar)

        btnpersonal.setOnClickListener {
            val errores = mutableListOf<String>() // Lista para acumular errores

            val nombreText = nombre.text.toString().trim()
            val apellidoText = apellido.text.toString().trim()
            val rolText = rol.selectedItemPosition + 1
            val telefonoText = vtelefono.text.toString().trim()
            val emailText = emai.text.toString().trim()
            val dniText = dni.text.toString().trim()

            val estadoSeleccionadoId = rgestado.checkedRadioButtonId
            val estadoBoolean = if (estadoSeleccionadoId != -1) {
                val estadoRadioButton: RadioButton = view.findViewById(estadoSeleccionadoId)
                estadoRadioButton.text.toString() == "Activo"
            } else {
                errores.add("Debe seleccionar un estado.")
                false
            }

            val fechanacText = fechanac.text.toString().trim()
            if (selectedDay2 == null) {
                errores.add("Debe seleccionar una fecha de nacimiento.")
            } else {
                val today = Calendar.getInstance()
                val birthDate = Calendar.getInstance()
                birthDate.time = selectedDay2!!
                val age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)
                if (age < 18 || (age == 18 && today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR))) {
                    errores.add("Debe ser mayor de 18 años.")
                }
            }

            val UbigeoText = Ubigeo.selectedItemPosition + 1
            val usuarioText = usuario.text.toString().trim()
            val contraseñaText = contraseña.text.toString().trim()

            // Validaciones de los campos
            if (nombreText.isEmpty()) errores.add("El nombre no puede estar vacío.")
            if (apellidoText.isEmpty()) errores.add("El apellido no puede estar vacío.")
            if (telefonoText.length > 10 || !telefonoText.all { it.isDigit() }) {
                errores.add("El número de teléfono debe tener maximo 10 dígitos y solo contener números.")
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                errores.add("Ingrese un correo electrónico válido.")
            }
            if (dniText.isEmpty() || dniText.length > 10 || !dniText.all { it.isDigit() }) {
                errores.add("El DNI debe tener hasta 10 dígitos y contener solo números.")
            }
            if (usuarioText.isEmpty()) errores.add("El usuario no puede estar vacío.")
            if (contraseñaText.isEmpty()) errores.add("La contraseña no puede estar vacía.")

            // Mostrar errores si los hay
            if (errores.isNotEmpty()) {
                val mensajeError = errores.joinToString("\n") // Combina los errores en una cadena de texto
                val toast = Toast.makeText(requireContext(), mensajeError, Toast.LENGTH_LONG)

                // Personalizar la posición del Toast para que aparezca arriba
                toast.setGravity(android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL, 0, 150)
                toast.show()

                return@setOnClickListener
            }

            val personal = PersonalModel(
                nombre = nombreText,
                apellido = apellidoText,
                idRol = rolText,
                telefono = telefonoText,
                correo = emailText,
                dni = dniText,
                Estado = estadoBoolean,
                fechNacimiento = fechanacText,
                idUbigeo = UbigeoText,
                username = usuarioText,
                password = contraseñaText
            )

            viewModel.createPersonal(personal)

            Toast.makeText(requireContext(), "Registrado con éxito.", Toast.LENGTH_SHORT).show()
                    // Limpiar campos
                    nombre.text = ""
                    apellido.text = ""
                    vtelefono.text = ""
                    emai.text = ""
                    dni.text = ""
                    fechanac.text = ""
                    usuario.text = ""
                    contraseña.text = ""


        }

        return view
    }
}