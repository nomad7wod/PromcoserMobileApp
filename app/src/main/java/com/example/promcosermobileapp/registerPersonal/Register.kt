package com.example.promcosermobileapp.registerPersonal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.promcosermobileapp.NavigationPromcoserActivity
import com.example.promcosermobileapp.R
import com.example.promcosermobileapp.login.LoginActivity
import com.example.promcosermobileapp.registerPersonal.model.RegisterModel
import com.example.promcosermobileapp.registerPersonal.repository.RegisterRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register : AppCompatActivity() {
    private lateinit var repository: RegisterRepository

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
        val idUbigeoField = findViewById<EditText>(R.id.ubigeoRegis)
        val usernameField = findViewById<EditText>(R.id.usuarioRegis)
        val passwordField = findViewById<EditText>(R.id.passwordRegis)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val request = RegisterModel.RegisterRequest(
                nombre = nombreField.text.toString(),
                apellido = apellidoField.text.toString(),
                telefono = telefonoField.text.toString(),
                correo = correoField.text.toString(),
                dni = dniField.text.toString(),
                fechNacimiento = fechNacimientoField.text.toString(),
                idUbigeo = idUbigeoField.text.toString().toIntOrNull() ?: 0,
                username = usernameField.text.toString(),
                password = passwordField.text.toString()
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
                        Toast.makeText(
                            this@Register,
                            "Registro exitoso: ${result.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this@Register, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@Register,
                            "Error: ${result?.message ?: "Respuesta inesperada"}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(this@Register, "Error en el servidor", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterModel.RegisterResponse>, t: Throwable) {
                Toast.makeText(this@Register, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
