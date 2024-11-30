package com.example.promcosermobileapp.registerPersonal

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.promcosermobileapp.R
import com.example.promcosermobileapp.login.LoginActivity
import com.example.promcosermobileapp.registerPersonal.model.RegisterModel
import com.example.promcosermobileapp.registerPersonal.repository.RegisterRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class Register : AppCompatActivity() {
    private lateinit var repository: RegisterRepository

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        repository = RegisterRepository()

        val nombreField = findViewById<EditText>(R.id.Register_nombre)
        val apellidoField = findViewById<EditText>(R.id.Register_apellido)
        val telefonoField = findViewById<EditText>(R.id.Register_telefono)
        val correoField = findViewById<EditText>(R.id.Register_correo)
        val dniField = findViewById<EditText>(R.id.Register_dni)
        val fechNacimientoField = findViewById<EditText>(R.id.dateFecha)
        val usernameField = findViewById<EditText>(R.id.usuarioRegis)
        val passwordField = findViewById<EditText>(R.id.passwordRegis)
        val confirmPasswordField = findViewById<EditText>(R.id.confirmaContra)
        val eyeIconPassword = findViewById<ImageView>(R.id.imgTogglePassword1)
        val eyeIconConfirmPassword = findViewById<ImageView>(R.id.imgTogglePassword2)
        val registerButton = findViewById<Button>(R.id.registerButton)

        eyeIconPassword.setOnClickListener {
            if (passwordField.inputType == (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                passwordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                eyeIconPassword.setImageResource(R.drawable.ojo_cerrado) // Closed eye icon
            } else {
                passwordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                eyeIconPassword.setImageResource(R.drawable.ojo_abierto) // Open eye icon
            }
            passwordField.setSelection(passwordField.text.length) // Keep cursor position
        }

        eyeIconConfirmPassword.setOnClickListener {
            if (confirmPasswordField.inputType == (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                confirmPasswordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                eyeIconConfirmPassword.setImageResource(R.drawable.ojo_cerrado) // Closed eye icon
            } else {
                confirmPasswordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                eyeIconConfirmPassword.setImageResource(R.drawable.ojo_abierto) // Open eye icon
            }
            confirmPasswordField.setSelection(confirmPasswordField.text.length) // Keep cursor position
        }


        fechNacimientoField.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = Calendar.getInstance().apply {
                    set(selectedYear, selectedMonth, selectedDay)
                }
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                fechNacimientoField.setText(dateFormat.format(selectedDate.time))
            }, year, month, day)

            datePicker.show()
        }

        registerButton.setOnClickListener {
            if (nombreField.text.isNullOrEmpty() ||
                apellidoField.text.isNullOrEmpty() ||
                telefonoField.text.isNullOrEmpty() ||
                correoField.text.isNullOrEmpty() ||
                dniField.text.isNullOrEmpty() ||
                fechNacimientoField.text.isNullOrEmpty() ||
                usernameField.text.isNullOrEmpty() ||
                passwordField.text.isNullOrEmpty() ||
                confirmPasswordField.text.isNullOrEmpty()
            ) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correoField.text.toString()).matches()) {
                Toast.makeText(this, "El correo no es válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (dniField.text.toString().length != 8 || dniField.text.toString().any { !it.isDigit() }) {
                Toast.makeText(this, "El DNI debe tener 8 dígitos numéricos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (passwordField.text.toString() != confirmPasswordField.text.toString()) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val request = RegisterModel.RegisterRequest(
                nombre = nombreField.text.toString().trim(),
                apellido = apellidoField.text.toString().trim(),
                telefono = telefonoField.text.toString().trim(),
                correo = correoField.text.toString().trim(),
                dni = dniField.text.toString().trim(),
                fechNacimiento = fechNacimientoField.text.toString().trim(),
                username = usernameField.text.toString().trim(),
                password = passwordField.text.toString().trim()
            )

            registerPersonal(request)
        }

    }

    private fun registerPersonal(request: RegisterModel.RegisterRequest) {
        repository.register(request).enqueue(object : Callback<RegisterModel.RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterModel.RegisterResponse>,
                response: Response<RegisterModel.RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result?.success == true) {
                        Toast.makeText(this@Register, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@Register, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@Register, "Error: ${result?.message}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@Register, "Error del servidor", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterModel.RegisterResponse>, t: Throwable) {
                Toast.makeText(this@Register, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
