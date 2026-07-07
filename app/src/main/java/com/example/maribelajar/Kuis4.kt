package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Kuis4 : AppCompatActivity() {

    private var skor = 0
    private var sudahMenjawab = false
    private val POIN_PER_BENAR = 4
    private val DELAY_DURATION = 1500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kuis4)

        skor = intent.getIntExtra("SKOR", 0)

        val btnA = findViewById<Button>(R.id.btnA)
        val btnB = findViewById<Button>(R.id.btnB)
        val btnB2 = findViewById<Button>(R.id.btnB2)

        // Jawaban Salah
        btnA.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                Toast.makeText(this, "❌ Salah! Coba lagi", Toast.LENGTH_SHORT).show()
                pindahKeKuis5(btnA, btnB, btnB2)
            }
        }

        // Jawaban Benar
        btnB.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                skor += POIN_PER_BENAR
                Toast.makeText(this, "🎉 Benar! +$POIN_PER_BENAR poin", Toast.LENGTH_SHORT).show()
                pindahKeKuis5(btnA, btnB, btnB2)
            }
        }

        // Jawaban Salah
        btnB2.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                Toast.makeText(this, "❌ Salah! Coba lagi", Toast.LENGTH_SHORT).show()
                pindahKeKuis5(btnA, btnB, btnB2)
            }
        }
    }

    private fun pindahKeKuis5(vararg buttons: Button) {
        disableButtons(*buttons)
        buttons[1].setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
        for (i in buttons.indices) {
            if (i != 1) {
                buttons[i].setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, Kuis5::class.java)
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