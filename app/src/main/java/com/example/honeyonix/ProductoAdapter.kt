package com.example.honeyonix
import Producto
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductoAdapter(private val productosList: List<Producto>) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    inner class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        val precioTextView: TextView = itemView.findViewById(R.id.precioTextView)
        val imagenImageView: ImageView = itemView.findViewById(R.id.imagenImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_productos, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productosList[position]
        holder.nombreTextView.text = producto.nombre
        holder.precioTextView.text = "MXN ${producto.precio}"

        // Usar Glide para cargar la imagen
        Glide.with(holder.itemView.context)
            .load(producto.imagenes.firstOrNull())
            .into(holder.imagenImageView)

        // Configurar el click listener
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, MostrarProducto::class.java).apply {
                putExtra("NOMBRE_PRODUCTO", producto.nombre)
                putExtra("PRECIO_PRODUCTO", producto.precio.toString())
                putExtra("DESCRIPCION_PRODUCTO", producto.descripcion)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return productosList.size
    }
}
