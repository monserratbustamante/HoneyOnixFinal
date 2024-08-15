package com.example.honeyonix

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.honeyonix.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Login"

        auth = FirebaseAuth.getInstance()

        // Navegar a la actividad de registro
        binding.registerTV.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        // Navegar a la actividad de recuperación de contraseña
        binding.forgotPasswordTV.setOnClickListener {
            startActivity(Intent(this, CambioPassActivity::class.java))
            finish()
        }

        // Manejar el inicio de sesión
        binding.loginBtn.setOnClickListener {
            val email = binding.emailLogin.text.toString().trim()
            val password = binding.passwordLogin.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Redirigir a la actividad principal después del inicio de sesión exitoso
                        startActivity(Intent(this, CatalogoActivity::class.java))
                        finish() // Finalizar la actividad de inicio de sesión
                    } else {
                        Toast.makeText(this, "Error en el inicio de sesión: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Error: ${it.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Por favor, ingresa el email y la contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
