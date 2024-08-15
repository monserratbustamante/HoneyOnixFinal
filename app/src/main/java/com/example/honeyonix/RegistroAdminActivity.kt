package com.example.honeyonix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class RegistroAdminActivity : AppCompatActivity() {

    private lateinit var nombreInput: TextInputEditText
    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var createAccountBtn: MaterialButton
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regitro_admin)

        nombreInput = findViewById(R.id.nombreRegister)
        emailInput = findViewById(R.id.emailRegister)
        passwordInput = findViewById(R.id.passwordRegister)
        createAccountBtn = findViewById(R.id.createAccountBtn)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Administradores")

        createAccountBtn.setOnClickListener {
            crearCuenta()
        }
    }

    private fun crearCuenta() {
        val nombre = nombreInput.text.toString().trim()
        val email = emailInput.text.toString().trim()
        val password = passwordInput.text.toString().trim()

        if (nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Guarda la información del administrador en Firebase Database
                    val userId = auth.currentUser?.uid
                    val admin = Administrador(nombre, email)
                    database.child(userId!!).setValue(admin)
                        .addOnCompleteListener { task1 ->
                            if (task1.isSuccessful) {
                                Toast.makeText(this, "Administrador registrado exitosamente", Toast.LENGTH_SHORT).show()
                            } else {
                                Log.e("RegistroAdmin", "Error al registrar en la base de datos: ${task1.exception?.message}")
                            }
                        }
                } else {
                    Log.e("RegistroAdmin", "Error en la autenticación: ${task.exception?.message}")
                    Toast.makeText(this, "Error al crear cuenta", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // Clase interna para el objeto Administrador
    data class Administrador(val nombre: String?, val email: String?)
}