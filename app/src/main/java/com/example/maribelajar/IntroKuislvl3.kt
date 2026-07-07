package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class IntroKuislvl3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.introkuislvl3)

        val tvJudul = findViewById<TextView>(R.id.tvJudulLevel)
        val tvDeskripsi = findViewById<TextView>(R.id.tvDeskripsiLevel)
        val tvLevel = findViewById<TextView>(R.id.tvLevel)
        val tvJumlahSoal = findViewById<TextView>(R.id.tvJumlahSoal)
        val tvPoinPerBenar = findViewById<TextView>(R.id.tvPoinPerBenar)
        val btnMulai = findViewById<Button>(R.id.btnMulaiLevel)

        // Ambil skor dari level sebelumnya
        val skorDariLevel2 = intent.getIntExtra("SKOR", 0)

        tvJudul.text = "📚 LEVEL 3"
        tvDeskripsi.text = "Menguasai Kemampuan Membaca dan Berhitung"
        tvLevel.text = "Level 3 - Sulit"
        tvJumlahSoal.text = "5 Soal"
        tvPoinPerBenar.text = "10 Poin"
        btnMulai.text = "🚀 Mulai Kuis Level 3"

        btnMulai.setOnClickListener {
            val intent = Intent(this, KuisLvl3::class.java)
            intent.putExtra("SKOR", skorDariLevel2)
            startActivity(intent)
            finish()
        }
    }
}