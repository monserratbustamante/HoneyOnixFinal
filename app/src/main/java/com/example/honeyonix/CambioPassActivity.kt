package com.example.honeyonix

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.honeyonix.databinding.ActivityCambioPassBinding
import com.google.firebase.auth.FirebaseAuth

class CambioPassActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityCambioPassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCambioPassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Definir el título de la acción
        supportActionBar?.title = "Recuperar Contraseña"

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Manejar el clic en el botón de regresar
        binding.regresar1.setOnClickListener {
            // Regresa a la actividad de inicio de sesión
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Handle Reset Password button click
        binding.RecuperarBtn.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()  // Limpiar espacios en blanco
            if (email.isNotEmpty()) {
                // Enviar correo de recuperación de contraseña
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Correo de recuperación enviado.", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "No se pudo enviar el correo de recuperación.", Toast.LENGTH_LONG).show()
                        }
                    }.addOnFailureListener { exception ->
                        // Maneja el caso en que hubo un error enviando el correo
                        Toast.makeText(this, "Error: ${exception.localizedMessage}", Toast.LENGTH_LONG).show()
                    }
            } else {
                Toast.makeText(this, "Por favor, ingrese su correo electrónico.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}