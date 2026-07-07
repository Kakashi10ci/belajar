package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Kuis5 : AppCompatActivity() {

    private var skor = 0
    private var sudahMenjawab = false
    private val POIN_PER_BENAR = 4
    private val DELAY_DURATION = 1500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kuis5)

        skor = intent.getIntExtra("SKOR", 0)

        val btnA = findViewById<Button>(R.id.btnA)
        val btnB = findViewById<Button>(R.id.btnB)
        val btnB3 = findViewById<Button>(R.id.btnB3)

        // Jawaban Benar
        btnA.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                skor += POIN_PER_BENAR
                Toast.makeText(this, "🎉 Benar! +$POIN_PER_BENAR poin", Toast.LENGTH_SHORT).show()
                pindahKeLevel2Intro(btnA, btnB, btnB3)
            }
        }

        // Jawaban Salah
        btnB.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                Toast.makeText(this, "❌ Salah! Coba lagi", Toast.LENGTH_SHORT).show()
                pindahKeLevel2Intro(btnA, btnB, btnB3)
            }
        }

        // Jawaban Salah
        btnB3.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                Toast.makeText(this, "❌ Salah! Coba lagi", Toast.LENGTH_SHORT).show()
                pindahKeLevel2Intro(btnA, btnB, btnB3)
            }
        }
    }

    private fun pindahKeLevel2Intro(vararg buttons: Button) {
        disableButtons(*buttons)
        buttons[0].setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
        for (i in buttons.indices) {
            if (i != 0) {
                buttons[i].setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            // PINDAH KE LEVEL2 INTRO
            val intent = Intent(this, IntroKuislvl2::class.java)
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