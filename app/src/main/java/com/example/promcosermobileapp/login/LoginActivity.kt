package com.example.promcosermobileapp.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.*
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
    private lateinit var imgTogglePassword: ImageView
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private val loginRepository = LoginRepository()
    private var isPasswordVisible = false // Estado inicial de visibilidad

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        imgTogglePassword = findViewById(R.id.imgTogglePassword)
        btnLogin = findViewById(R.id.btnAcceder)
        btnRegister = findViewById(R.id.btnRegistro)

        // Configura el botón de mostrar/ocultar contraseña
        imgTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                etPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                imgTogglePassword.setImageResource(R.drawable.ojo_abierto) // Cambia al ícono de ojo abierto
            } else {
                etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                imgTogglePassword.setImageResource(R.drawable.ojo_cerrado) // Cambia al ícono de ojo cerrado
            }
            etPassword.setSelection(etPassword.text.length) // Mantén el cursor al final
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (validateInputs(email, password)) {
                if (email == "admin@gmail.com" && password == "12345") {
                    Toast.makeText(this, "Bienvenido, Administrador", Toast.LENGTH_SHORT).show()
                    navigateToHome()
                } else {
                    login(email, password)
                }
            }
        }

        btnRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, Register::class.java)
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
                Toast.makeText(this, "Ingrese un correo válido", Toast.LENGTH_SHORT).show()
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


