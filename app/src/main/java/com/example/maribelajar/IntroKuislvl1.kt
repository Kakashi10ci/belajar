package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class IntroKuislvl1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.introkuislvl1)

        val tvJudul = findViewById<TextView>(R.id.tvJudulLevel)
        val tvDeskripsi = findViewById<TextView>(R.id.tvDeskripsiLevel)
        val tvLevel = findViewById<TextView>(R.id.tvLevel)
        val tvJumlahSoal = findViewById<TextView>(R.id.tvJumlahSoal)
        val tvPoinPerBenar = findViewById<TextView>(R.id.tvPoinPerBenar)
        val btnMulai = findViewById<Button>(R.id.btnMulaiLevel)

        tvJudul.text = "📚 LEVEL 1"
        tvDeskripsi.text = "Belajar Dasar-dasar Membaca dan Menulis"
        tvLevel.text = "Level 1 - Mudah"
        tvJumlahSoal.text = "5 Soal"
        tvPoinPerBenar.text = "4 Poin"
        btnMulai.text = "🚀 Mulai Kuis Level 1"

        btnMulai.setOnClickListener {
            val intent = Intent(this, KuisLvl1::class.java)
            intent.putExtra("SKOR", 0)
            startActivity(intent)
            finish()
        }
    }
}