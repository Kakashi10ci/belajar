package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class IntroKuislvl2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.introkuislvl2)

        val tvJudul = findViewById<TextView>(R.id.tvJudulLevel)
        val tvDeskripsi = findViewById<TextView>(R.id.tvDeskripsiLevel)
        val tvLevel = findViewById<TextView>(R.id.tvLevel)
        val tvJumlahSoal = findViewById<TextView>(R.id.tvJumlahSoal)
        val tvPoinPerBenar = findViewById<TextView>(R.id.tvPoinPerBenar)
        val btnMulai = findViewById<Button>(R.id.btnMulaiLevel)

        // Ambil skor dari level sebelumnya
        val skorDariLevel1 = intent.getIntExtra("SKOR", 0)

        tvJudul.text = "📚 LEVEL 2"
        tvDeskripsi.text = "Meningkatkan Kemampuan Membaca dan Berhitung"
        tvLevel.text = "Level 2 - Sedang"
        tvJumlahSoal.text = "5 Soal"
        tvPoinPerBenar.text = "6 Poin"
        btnMulai.text = "🚀 Mulai Kuis Level 2"

        btnMulai.setOnClickListener {
            val intent = Intent(this, KuisLvl2::class.java)
            intent.putExtra("SKOR", skorDariLevel1)
            startActivity(intent)
            finish()
        }
    }
}