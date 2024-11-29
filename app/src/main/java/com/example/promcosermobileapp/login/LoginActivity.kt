package com.example.promcosermobileapp.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.promcosermobileapp.NavigationPromcoserActivity
import com.example.promcosermobileapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private val authService: AuthService = ApiClient.retrofit.create(AuthService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnAcceder)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Validación de campos
            when {
                email.isEmpty() || password.isEmpty() -> {
                    Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                }
                !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    Toast.makeText(this, "Ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show()
                }
                else -> login(email, password)
            }
        }
    }

    private fun login(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)

        authService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                when {
                    response.isSuccessful -> {
                        val loginResponse = response.body()
                        if (loginResponse?.success == true) {
                            // Login exitoso
                            Toast.makeText(this@LoginActivity, "Login Exitoso", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, NavigationPromcoserActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // Credenciales incorrectas
                            Toast.makeText(this@LoginActivity, loginResponse?.message ?: "Credenciales Incorrectas", Toast.LENGTH_SHORT).show()
                        }
                    }
                    response.code() == 404 -> {
                        // Usuario no encontrado
                        Toast.makeText(this@LoginActivity, "Usuario no encontrado. Verifique las credenciales.", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        // Otros errores del servidor
                        Toast.makeText(this@LoginActivity, "Error en la solicitud: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Error de conexión
                Toast.makeText(this@LoginActivity, "Error en la conexión: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

