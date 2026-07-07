package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BerandaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beranda)

        val btnMembaca = findViewById<Button>(R.id.btnMembaca)
        val btnBerhitung = findViewById<Button>(R.id.btnBerhitung)
        val btnKuis = findViewById<Button>(R.id.btnKuis)  // Hanya 1 tombol Kuis
        val btnHasil = findViewById<Button>(R.id.btnHasil)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        // Tombol Membaca
        btnMembaca.setOnClickListener {
            val intent = Intent(this, MembacaActivity::class.java)
            startActivity(intent)
        }

        // Tombol Berhitung
        btnBerhitung.setOnClickListener {
            val intent = Intent(this, BerhitungActivity::class.java)
            startActivity(intent)
        }

        // Tombol Kuis (Hanya 1)
        btnKuis.setOnClickListener {
            val intent = Intent(this, IntroKuislvl1::class.java)
            startActivity(intent)
        }

        // Tombol Hasil (Bentuk Bulat)
        btnHasil.setOnClickListener {
            val intent = Intent(this, HasilKuis::class.java)
            startActivity(intent)
        }

        // Tombol Kembali
        btnKembali.setOnClickListener {
            finish()
        }
    }
}