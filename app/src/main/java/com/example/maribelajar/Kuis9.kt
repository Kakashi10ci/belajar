package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Kuis9 : AppCompatActivity() {

    private var skor = 0
    private var sudahMenjawab = false
    private val POIN_PER_BENAR = 6
    private val DELAY_DURATION = 1500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kuis9)

        skor = intent.getIntExtra("SKOR", 0)

        val btnA = findViewById<Button>(R.id.btnA)
        val btnB = findViewById<Button>(R.id.btnB)
        val btnC = findViewById<Button>(R.id.btnC)
        val btnD = findViewById<Button>(R.id.btnD)

        // Jawaban Salah
        btnA.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                Toast.makeText(this, "❌ Jawaban Salah! Coba lagi", Toast.LENGTH_SHORT).show()
                pindahKeLevel3Intro(btnA, btnB, btnC, btnD)
            }
        }

        // Jawaban Salah
        btnB.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                Toast.makeText(this, "❌ Jawaban Salah! Coba lagi", Toast.LENGTH_SHORT).show()
                pindahKeLevel3Intro(btnA, btnB, btnC, btnD)
            }
        }

        // Jawaban Benar (8) - Dapat 6 POIN
        btnC.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                skor += POIN_PER_BENAR

                Toast.makeText(
                    this,
                    "✅ Jawaban Benar! +$POIN_PER_BENAR poin",
                    Toast.LENGTH_SHORT
                ).show()

                pindahKeLevel3Intro(btnA, btnB, btnC, btnD)
            }
        }

        // Jawaban Salah
        btnD.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                Toast.makeText(this, "❌ Jawaban Salah! Coba lagi", Toast.LENGTH_SHORT).show()
                pindahKeLevel3Intro(btnA, btnB, btnC, btnD)
            }
        }
    }

    private fun pindahKeLevel3Intro(vararg buttons: Button) {
        disableButtons(*buttons)

        // Tandai jawaban benar (btnC) dengan hijau
        buttons[2].setBackgroundColor(resources.getColor(android.R.color.holo_green_light))

        // Tandai jawaban salah dengan merah
        for (i in buttons.indices) {
            if (i != 2) {
                buttons[i].setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            // PINDAH KE LEVEL3 INTRO
            val intent = Intent(this, IntroKuislvl3::class.java)
            intent.putExtra("SKOR", skor)
            startActivity(intent)
            finish()
        }, DELAY_DURATION)
    }

    private fun disableButtons(vararg buttons: Button) {
        for (btn in buttons) {
            btn.isEnabled = false
        }
    }
}