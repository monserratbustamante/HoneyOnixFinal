package com.example.honeyonix

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.honeyonix.databinding.ActivityCarritoBinding

class CarritoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarritoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCarritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.regresar1.setOnClickListener {
            startActivity(Intent(this,CatalogoActivity ::class.java))
            finish()
        }

       /* binding.FinaliarCompra.setOnClickListener {
            startActivity(Intent(this, PagoActivity::class.java))
            finish()
        }*/
    }
}