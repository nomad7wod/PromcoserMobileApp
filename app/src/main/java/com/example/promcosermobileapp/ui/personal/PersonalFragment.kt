package com.example.promcosermobileapp.ui.personal

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
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
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.promcosermobileapp.R
import com.example.promcosermobileapp.databinding.FragmentPersonalBinding
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
        val view : View = inflater.inflate(R.layout.fragment_personal, container, false)

        val btnhist: Button = view.findViewById(R.id.btnhistorial)
        val navController = findNavController()
        btnhist.setOnClickListener {
            navController.navigate(R.id.personalApiFragment)
        }


        val nombre: TextView = view.findViewById(R.id.etnombres)
        val apellido: TextView = view.findViewById(R.id.etapellidos)
        val rol: Spinner = view.findViewById(R.id.spRol)

        viewModel.roles.observe(viewLifecycleOwner) { roles ->
            if (roles.isNotEmpty()) {
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    roles.map { it.descripcion } // Usamos `descripcion` para llenar el Spinner
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
        val rgestado: RadioGroup = view.findViewById(R.id.rgestado) //radio gruoup
        val fechanac: TextView = view.findViewById(R.id.etfechanac)
        fechanac.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _: DatePicker, SelectedYear: Int, selectedMonth: Int, selectedDay: Int ->

                    val CalSel = Calendar.getInstance()

                    CalSel.set(SelectedYear, selectedMonth, selectedDay)

                    selectedDay2 = Date(CalSel.timeInMillis)
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).format(selectedDay2 ?: java.util.Date())
                    val formattedDate = dateFormat.format(selectedDay2 ?: java.util.Date())
                    fechanac.text = formattedDate.format(selectedDay2 ?: java.util.Date())
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
        val Ubigeo: Spinner = view.findViewById(R.id.spLocalidad)
        //carga ubigeo
        viewModel.ubigeo.observe(viewLifecycleOwner) { ubigeo ->
            if (ubigeo.isNotEmpty()) {
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    ubigeo.map { "${it.departamento} - ${it.provincia} - ${it.distrito}" } // Usamos `descripcion` para llenar el Spinner
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                Ubigeo.adapter = adapter
                Log.d("Spinner", "Roles cargados: ${ubigeo.map { it.departamento + it.provincia + it.distrito }}")
            }else{
                Log.e("Spinner", "Lista de roles vacía")
            }

            }
        viewModel.loadUbigeo()

        val usuario: TextView = view.findViewById(R.id.etusuario)
        val contraseña: TextView = view.findViewById(R.id.etcontraseña)
        val btnpersonal: Button = view.findViewById(R.id.btnRegistrar)




        btnpersonal.setOnClickListener {
            val nombreText = nombre.text.toString()
            val apellidoText = apellido.text.toString()
            val rolText = rol.selectedItemPosition + 1
            val telefonoText = vtelefono.text.toString()
            val emailText = emai.text.toString()
            val dniText = dni.text.toString()

            val estadoSeleccionadoId = rgestado.checkedRadioButtonId
            val estadoRadioButton: RadioButton = view.findViewById(estadoSeleccionadoId)
            val estadoBoolean = estadoRadioButton.text.toString() == "Activo"

            val fechanacText = fechanac.text.toString()

            val UbigeoText = viewModel.ubigeo.value?.get(Ubigeo.selectedItemPosition)?.id_ubigeo.toString()
            val usuarioText = usuario.text.toString()
            val contraseñaText = contraseña.text.toString()


            Log.d("DatosEnviados", """
                        Nombre: $nombreText
                        Apellido: $apellidoText
                        Rol: $rolText
                        Teléfono: $telefonoText
                        Email: $emailText
                        DNI: $dniText
                        Estado: $estadoBoolean
                        Fecha de Nacimiento: $fechanacText
                        Ubigeo ID: $UbigeoText
                        Usuario: $usuarioText
                        Contraseña: $contraseñaText
                    """.trimIndent())


            //crear objeto
            val personal = PersonalModel(
                nombre = nombreText,
                apellido = apellidoText,
                id_rol = rolText.toInt(),
                telefono =  telefonoText,
                correo = emailText,
                dni = dniText,
                estado = estadoBoolean,
                fech_nacimiento = selectedDay2?: Date(0),
                id_ubigeo = UbigeoText.toInt(),
                username = usuarioText,
                password = contraseñaText
            )
            Log.d("DatosEnviados", Gson().toJson(personal))

            viewModel.createPersonal(personal)
        }



        return view
    }
}