package com.example.honeyonix

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImagenAdapter(private val imagenUris: List<Uri>) : RecyclerView.Adapter<ImagenAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView =
            itemView.findViewById(R.id.item_imagen) // Asegúrate de que tienes un layout para los items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_imagen_selecciona,
                parent,
                false
            ) // Asegúrate de tener un layout llamado item_imagen_selecciona.xml
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uri = imagenUris[position]
        Glide.with(holder.itemView.context)
            .load(uri)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return imagenUris.size
    }

}