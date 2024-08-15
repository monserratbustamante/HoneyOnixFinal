package com.example.honeyonix

import Producto
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class RegistroProductActivity : AppCompatActivity() {

    private lateinit var etNombres: EditText
    private lateinit var etPrecio: EditText
    private lateinit var etDescripcion: EditText
    private lateinit var etCantidad: EditText
    private lateinit var btnAgregar: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var imagenAdapter: ImagenAdapter

    private var imagenUris: MutableList<Uri> = mutableListOf()

    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var imageAgregarP: ImageView

    private companion object {
        private const val PICK_IMAGE_REQUEST = 1
        private const val PERMISSION_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_product)

        // Inicializando Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("productos")
        storageReference = FirebaseStorage.getInstance().getReference("imagenes")

        etNombres = findViewById(R.id.etNombres)
        etPrecio = findViewById(R.id.etPrecio)
        etDescripcion = findViewById(R.id.etDescripcion)
        etCantidad = findViewById(R.id.etCantidad)
        btnAgregar = findViewById(R.id.btnAgregar)
        imageAgregarP = findViewById(R.id.imageAgregarP)
        recyclerView = findViewById(R.id.RT_ImagenProduct)

        // Configuración del RecyclerView
        imagenAdapter = ImagenAdapter(imagenUris)
        recyclerView.adapter = imagenAdapter

        // Click para seleccionar imágenes
        imageAgregarP.setOnClickListener {
            if (checkPermissions()) {
                abrirGaleria()
            }
        }

        // Click para agregar producto
        btnAgregar.setOnClickListener { agregarProducto() }
    }

    private fun checkPermissions(): Boolean {
        return if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
            false
        } else {
            true
        }
    }

    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true) // Permitir selección múltiple
        }
        startActivityForResult(Intent.createChooser(intent, "Selecciona imágenes"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.let {
                if (it.clipData != null) { // Si se seleccionan múltiples imágenes
                    val count = it.clipData!!.itemCount
                    for (i in 0 until count) {
                        val uri = it.clipData!!.getItemAt(i).uri
                        imagenUris.add(uri)
                    }
                } else if (it.data != null) { // Si solo se selecciona una imagen
                    val uri = it.data
                    imagenUris.add(uri!!)
                }
                imagenAdapter.notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
            }
        }
    }

    private fun agregarProducto() {
        val nombre = etNombres.text.toString().trim()
        val precioStr = etPrecio.text.toString().trim()
        val descripcion = etDescripcion.text.toString().trim()
        val cantidadStr = etCantidad.text.toString().trim()

        // Convertir a Double e Int después de verificar si están vacíos
        val precio = precioStr.toDoubleOrNull() ?: 0.0
        val cantidad = cantidadStr.toIntOrNull() ?: 0

        if (nombre.isEmpty() || precioStr.isEmpty() || descripcion.isEmpty() || cantidadStr.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val productId = databaseReference.push().key ?: return

        // Crear un objeto de producto
        val product = Producto(productId, nombre, precio, descripcion, cantidad)

        // Guardar el producto en la base de datos
        databaseReference.child(productId).setValue(product).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Subir imágenes al almacenamiento de Firebase
                uploadImages(productId)
                // Limpiar los campos después de guardar
                limpiarCampos()
            } else {
                Toast.makeText(this, "Error al agregar el producto", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun uploadImages(productId: String) {
        for (uri in imagenUris) {
            val fileReference = storageReference.child("$productId/${System.currentTimeMillis()}.${getFileExtension(uri)}")
            fileReference.putFile(uri).addOnSuccessListener {
                fileReference.downloadUrl.addOnSuccessListener { uri ->
                    val imageUrl = uri.toString()
                    databaseReference.child(productId).child("imagenes").push().setValue(imageUrl)
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Error al subir la imagen", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getFileExtension(uri: Uri): String? {
        return contentResolver.getType(uri)?.split("/")?.last()
    }

    private fun limpiarCampos() {
        etNombres.text.clear()
        etPrecio.text.clear()
        etDescripcion.text.clear()
        etCantidad.text.clear()
        imagenUris.clear()
        imagenAdapter.notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
    }
}
