package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Kuis6 : AppCompatActivity() {

    private var skor = 0
    private var sudahMenjawab = false
    private val POIN_PER_BENAR = 6
    private val DELAY_DURATION = 1500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kuis6)

        skor = intent.getIntExtra("SKOR", 0)

        val btnA = findViewById<Button>(R.id.btnA)
        val btnB = findViewById<Button>(R.id.btnB)
        val btnC = findViewById<Button>(R.id.btnC)
        val btnD = findViewById<Button>(R.id.btnD)

        // Jawaban Benar (N)
        btnA.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                skor += POIN_PER_BENAR
                Toast.makeText(this, "🎉 Benar! +$POIN_PER_BENAR poin", Toast.LENGTH_SHORT).show()
                pindahKeKuis7(btnA, btnB, btnC, btnD)
            }
        }

        // Jawaban Salah
        btnB.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                Toast.makeText(this, "❌ Salah! Coba lagi", Toast.LENGTH_SHORT).show()
                pindahKeKuis7(btnA, btnB, btnC, btnD)
            }
        }

        // Jawaban Salah
        btnC.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                Toast.makeText(this, "❌ Salah! Coba lagi", Toast.LENGTH_SHORT).show()
                pindahKeKuis7(btnA, btnB, btnC, btnD)
            }
        }

        // Jawaban Salah
        btnD.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                Toast.makeText(this, "❌ Salah! Coba lagi", Toast.LENGTH_SHORT).show()
                pindahKeKuis7(btnA, btnB, btnC, btnD)
            }
        }
    }

    private fun pindahKeKuis7(vararg buttons: Button) {
        disableButtons(*buttons)
        buttons[0].setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
        for (i in buttons.indices) {
            if (i != 0) {
                buttons[i].setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, Kuis7::class.java)
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