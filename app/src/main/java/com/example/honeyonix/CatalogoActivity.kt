package com.example.honeyonix

import Producto
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.honeyonix.databinding.ActivityCatalogoBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class CatalogoActivity : ComponentActivity() {

    private lateinit var binding: ActivityCatalogoBinding
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar el binding
        binding = ActivityCatalogoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Configurar el PopupMenu después de que se haya establecido el contenido de la vista

        // Configurar los click listeners para otros elementos del menú inferior
        binding.Perfil.setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))
            finish()
        }

        binding.Explorar.setOnClickListener {
            startActivity(Intent(this, RegistroProductActivity::class.java))
            finish()
        }
        binding.Menu.setOnClickListener {
            startActivity(Intent(this, PerfilAdminActivity::class.java))
            finish()
        }

        // Configurar RecyclerView con datos (ejemplo)
        setupRecyclerView()

        // Configurar el campo de búsqueda
       // setupSearch()
    }



    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = binding.recyclerViewProductos
        recyclerView.layoutManager = LinearLayoutManager(this)

        val productosList = listOf(
            Producto(
                id = "-O4DgN7w7AKHHGu8a3GC",
                nombre = "Cera estampada",
                precio = 155.0,
                descripcion = "cera estampada cámara precio por kilo",
                cantidad = 100,
                imagenes = listOf(
                    "https://firebasestorage.googleapis.com/v0/b/honeyonix-a97ce.appspot.com/o/imagenes%2F-O4DgN7w7AKHHGu8a3GC%2F1723602141698.jpeg?alt=media&token=63c37567-aa16-4d60-8c0d-ce53d40d6406",
                    "https://firebasestorage.googleapis.com/v0/b/honeyonix-a97ce.appspot.com/o/imagenes%2F-O4DgN7w7AKHHGu8a3GC%2F1723602141632.jpeg?alt=media&token=7d82c5ef-fb9d-45b4-999b-115d3785761d",
                    "https://firebasestorage.googleapis.com/v0/b/honeyonix-a97ce.appspot.com/o/imagenes%2F-O4DgN7w7AKHHGu8a3GC%2F1723602141717.jpeg?alt=media&token=5793b467-71c2-478d-8ad1-5b1a245b8589",
                    "https://firebasestorage.googleapis.com/v0/b/honeyonix-a97ce.appspot.com/o/imagenes%2F-O4DgN7w7AKHHGu8a3GC%2F1723602141735.jpeg?alt=media&token=41366e5d-45b9-47dd-a664-730f53435308"
                )
            ),
            Producto(
                id = "-O4Dh1lujbDxdZjpuIl8",
                nombre = "Miel pura",
                precio = 150.0,
                descripcion = "miel pura de diferentes cosechas",
                cantidad = 20,
                imagenes = listOf(
                    "https://firebasestorage.googleapis.com/v0/b/honeyonix-a97ce.appspot.com/o/imagenes%2F-O4Dh1lujbDxdZjpuIl8%2F1723602316951.jpeg?alt=media&token=6d0ce766-6478-4d4a-bca0-2c3bdb1be75b"
                )
            ),
            Producto(
                id = "-O4DjucN3f3HGWtPwSMq",
                nombre = "Propole",
                precio = 70.0,
                descripcion = "propóleo pura para diferentes enfermedades",
                cantidad = 1,
                imagenes = listOf(
                    "https://firebasestorage.googleapis.com/v0/b/honeyonix-a97ce.appspot.com/o/imagenes%2F-O4DjucN3f3HGWtPwSMq%2F1723603069382.jpeg?alt=media&token=06c0e66f-7315-443a-a046-d2958196e8e3",
                    "https://firebasestorage.googleapis.com/v0/b/honeyonix-a97ce.appspot.com/o/imagenes%2F-O4DjucN3f3HGWtPwSMq%2F1723603069341.jpeg?alt=media&token=4ab3fe13-db01-44db-beef-355c043162e0"
                )
            ),
            Producto(
                id = "-O4DkfzRuLC5Xzm4D-j4",
                nombre = "Dulces de propole",
                precio = 200.0,
                descripcion = "dulces de perla o gomitas de propóleo para tratar diferentes dolores de garganta",
                cantidad = 8,
                imagenes = listOf(
                    "https://firebasestorage.googleapis.com/v0/b/honeyonix-a97ce.appspot.com/o/imagenes%2F-O4DkfzRuLC5Xzm4D-j4%2F1723603271506.jpeg?alt=media&token=0a756ff7-89f5-44c2-b08f-d58d49b92659",
                    "https://firebasestorage.googleapis.com/v0/b/honeyonix-a97ce.appspot.com/o/imagenes%2F-O4DkfzRuLC5Xzm4D-j4%2F1723603271471.jpeg?alt=media&token=4d11f5af-88af-45fd-9ce2-c9d5b61fedbb",
                    "https://firebasestorage.googleapis.com/v0/b/honeyonix-a97ce.appspot.com/o/imagenes%2F-O4DkfzRuLC5Xzm4D-j4%2F1723603271525.jpeg?alt=media&token=5dfbe253-8a26-462c-9954-c661e1782e2f",
                    "https://firebasestorage.googleapis.com/v0/b/honeyonix-a97ce.appspot.com/o/imagenes%2F-O4DkfzRuLC5Xzm4D-j4%2F1723603271540.jpeg?alt=media&token=0c658b42-d344-4c97-bb08-ee661e8a342a"
                )
            ),
            Producto(
                id = "-O4DlWUY-zo2LxDve1Jr",
                nombre = "Cera en marqueta",
                precio = 200.0,
                descripcion = "cera en marqueta de 5 o 10 kilos",
                cantidad = 9,
                imagenes = listOf(
                    "https://firebasestorage.googleapis.com/v0/b/honeyonix-a97ce.appspot.com/o/imagenes%2F-O4DlWUY-zo2LxDve1Jr%2F1723603491286.jpeg?alt=media&token=939f2ba5-8edf-488a-88b9-838f387d6f2e",
                    "https://firebasestorage.googleapis.com/v0/b/honeyonix-a97ce.appspot.com/o/imagenes%2F-O4DlWUY-zo2LxDve1Jr%2F1723603491333.jpeg?alt=media&token=72401c2d-1fca-4aea-8012-702f2338bf3d"
                )
            ),
                Producto(
                    id = "-O4JMC4RJScjSlv3jat_",
                    nombre = "Humador",
                    precio = 300.0,
                    descripcion = "humador para panales de abejas",
                    cantidad = 20,
                    imagenes = listOf(
                        "https://firebasestorage.googleapis.com/v0/b/honeyonix-a97ce.appspot.com/o/imagenes%2F-O4JMC4RJScjSlv3jat_%2F1723697254411.jpeg?alt=media&token=0ed21c09-1ebe-4e8f-8017-31e81e6499e5"
                    )
                ),
                Producto(
                    id = "-O4JMQne7yfSmTXRCFFg",
                    nombre = "Traje de apicultura",
                    precio = 1500.0,
                    descripcion = "traje para apicultores protector contra picaduras de abeja",
                    cantidad = 150,
                    imagenes = listOf(
                        "https://firebasestorage.googleapis.com/v0/b/honeyonix-a97ce.appspot.com/o/imagenes%2F-O4JMQne7yfSmTXRCFFg%2F1723697314726.jpeg?alt=media&token=71fdf6f4-212d-4585-9c1b-5efd0ff6e910",
                        "https://firebasestorage.googleapis.com/v0/b/honeyonix-a97ce.appspot.com/o/imagenes%2F-O4JMQne7yfSmTXRCFFg%2F1723697314693.jpeg?alt=media&token=61f04ae5-191c-4386-837d-f9ed1e1f018c"

                )
            )
            )


        val adapter = ProductoAdapter(productosList)
        recyclerView.adapter = adapter
    }

    private fun logout() {
        googleSignInClient.signOut().addOnCompleteListener(this) {
            val sharedPref = getSharedPreferences("myPrefs", MODE_PRIVATE)
            sharedPref?.edit()?.clear()?.apply()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
