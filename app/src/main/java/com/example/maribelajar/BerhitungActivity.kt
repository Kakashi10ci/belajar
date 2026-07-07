package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class BerhitungActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.berhitung)

        val btnBelajarAngka = findViewById<Button>(R.id.btnBelajarAngka)

        btnBelajarAngka.setOnClickListener {
            val intent = Intent(this, BelajarAngka::class.java)
            startActivity(intent)
        }
    }
}