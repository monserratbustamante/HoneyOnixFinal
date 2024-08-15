package com.example.honeyonix

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MostrarProducto : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_producto)

        val whatsappButton = findViewById<Button>(R.id.whatsapp_button)
        val cantidadEditText = findViewById<EditText>(R.id.cantidadEditText)
        val montoTotalTextView = findViewById<TextView>(R.id.montoTotalTextView)

        // Obtener los datos enviados desde la actividad anterior
        val nombreProducto = intent.getStringExtra("NOMBRE_PRODUCTO")
        val precioProducto = intent.getStringExtra("PRECIO_PRODUCTO")?.toDoubleOrNull() ?: 0.0
        val descripcionProducto = intent.getStringExtra("DESCRIPCION_PRODUCTO")
        val imagenProductoUrl = intent.getStringExtra("IMAGEN_PRODUCTO_URL")

        // Configurar los TextViews con la información recibida
        val nombreTextView = findViewById<TextView>(R.id.nombreTextView)
        val precioTextView = findViewById<TextView>(R.id.precioTextView)
        val descripcionTextView = findViewById<TextView>(R.id.descripcionTextView)

        nombreTextView.text = nombreProducto
        precioTextView.text = precioProducto.toString()
        descripcionTextView.text = descripcionProducto

        // Cargar la imagen usando Glide (puedes agregar tu código aquí)


        // Configurar el botón de WhatsApp
        whatsappButton.setOnClickListener {
            val phoneNumber = "2212515890"  // Asegúrate de usar el número en formato internacional

            // Crear el mensaje con la información del producto y la cantidad seleccionada
            val cantidad = cantidadEditText.text.toString().toIntOrNull() ?: 1
            val montoTotal = precioProducto * cantidad
            val mensaje = "Hola, estoy interesado en el producto: $nombreProducto.\n" +
                    "Descripción: $descripcionProducto.\n" +
                    "Precio: $precioProducto.\n" +
                    "Cantidad: $cantidad.\n" +
                    "Monto Total: $$montoTotal.\n" 


            // Codificar el mensaje en la URL
            val url = "https://api.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(mensaje)}"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)

            // Verificar si WhatsApp está instalado
            try {
                startActivity(intent)
            } catch (e: Exception) {
                // Mostrar un mensaje si WhatsApp no está instalado
                Toast.makeText(this, "WhatsApp no está instalado en este dispositivo.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

