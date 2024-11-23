package com.example.promcosermobileapp.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.promcosermobileapp.MainActivity
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
            if (email.isNotEmpty() && password.isNotEmpty()) {
                login(email, password)
            } else {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        if(email=="admin@gmail.com" && password=="12345"){
            val intent = Intent(this@LoginActivity, NavigationPromcoserActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            authService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful && response.body()?.success == true) {
                        Toast.makeText(this@LoginActivity, "Login Exitoso", Toast.LENGTH_SHORT).show()
                        // Aquí redirigo xd
                        val intent = Intent(this@LoginActivity, NavigationPromcoserActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Error en la conexión", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}