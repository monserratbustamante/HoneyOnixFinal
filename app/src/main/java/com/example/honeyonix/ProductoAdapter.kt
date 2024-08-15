package com.example.honeyonix

import Producto
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductoAdapter(private val productos: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_productos, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.bind(producto)
    }

    override fun getItemCount() = productos.size

    inner class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImage: ImageView = itemView.findViewById(R.id.imagenImageView)
        private val productName: TextView = itemView.findViewById(R.id.nombreTextView)
        private val productPrice: TextView = itemView.findViewById(R.id.precioTextView)


        fun bind(producto: Producto) {
            productName.text = producto.nombre
            productPrice.text = "${producto.precio} MXN"
            // Carga la primera imagen desde la lista
            Glide.with(itemView.context).load(producto.imagenes.firstOrNull()).into(productImage)
        }
    }
}
