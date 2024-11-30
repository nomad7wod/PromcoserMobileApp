package com.example.promcosermobileapp.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.promcosermobileapp.NavigationPromcoserActivity
import com.example.promcosermobileapp.R
import com.example.promcosermobileapp.login.model.LoginRequest
import com.example.promcosermobileapp.login.model.LoginResponse
import com.example.promcosermobileapp.login.repository.LoginRepository
import com.example.promcosermobileapp.registerPersonal.Register
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private val loginRepository = LoginRepository() // Instancia del repositorio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnAcceder)
        btnRegister = findViewById(R.id.btnRegistro)
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (validateInputs(email, password)) {
                login(email, password)
            }
        }
        btnRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        return when {
            email.isEmpty() || password.isEmpty() -> {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                Toast.makeText(this, "Ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }

    private fun login(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)

        loginRepository.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse?.success == true) {
                        Toast.makeText(this@LoginActivity, loginResponse.message, Toast.LENGTH_SHORT).show()
                        navigateToHome()
                    } else {
                        Toast.makeText(this@LoginActivity, loginResponse?.message ?: "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error en la conexión: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun navigateToHome() {
        val intent = Intent(this, NavigationPromcoserActivity::class.java)
        startActivity(intent)
        finish()
    }
}

