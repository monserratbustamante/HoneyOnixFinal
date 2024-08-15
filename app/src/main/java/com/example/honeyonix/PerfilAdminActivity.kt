package com.example.honeyonix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class PerfilAdminActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private lateinit var adminName: TextView
    private lateinit var btnChangePassword: Button
    private lateinit var btnLogout: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_admin)

        // Initialize Firebase components
        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Initialize Google Sign-In Client
        mGoogleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build())

        // Link UI elements
        adminName = findViewById(R.id.adminName)
        btnChangePassword = findViewById(R.id.btnChangePassword)
        btnLogout = findViewById(R.id.btnLogout)
        backButton = findViewById(R.id.regresar)

        // Set admin name
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            adminName.text = "Bienvenid@ ${currentUser.displayName ?: "Admin"}"
        }

        // Set up button actions
        btnChangePassword.setOnClickListener {
            startActivity(Intent(this, CambioPassActivity::class.java))
        }

        btnLogout.setOnClickListener {
            signOut()
        }

        backButton.setOnClickListener {
            finish() // Go back to the previous activity
        }
    }

    private fun signOut() {
        // Sign out from Firebase
        mAuth.signOut()

        // Sign out from Google
        mGoogleSignInClient.signOut().addOnCompleteListener(this) {
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()

            // Redirect to LoginActivity
            val intent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            finish()
        }
    }
}
