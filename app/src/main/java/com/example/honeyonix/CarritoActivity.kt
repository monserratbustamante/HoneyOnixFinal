package com.example.honeyonix

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CarritoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)
        val btnWhatsApp = findViewById<Button>(R.id.btnWhatsApp)


        btnWhatsApp.setOnClickListener {
            val phoneNumber = "+2211479338" // Número de teléfono con código de país
            val message = "Hola, me gustaría obtener más información."

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://wa.me/$phoneNumber?text=${Uri.encode(message)}")
            startActivity(intent)
        }
    }
}
