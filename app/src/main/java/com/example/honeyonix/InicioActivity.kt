package com.example.honeyonix

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.honeyonix.RegisterActivity
import com.example.honeyonix.databinding.ActivityInicioBinding
import com.google.firebase.auth.FirebaseAuth


class InicioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInicioBinding

    companion object{
        lateinit var auth: FirebaseAuth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.linear3.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

    }



    private fun updateData(): String{
        return "Email : ${auth.currentUser?.email}"
    }
}