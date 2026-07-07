package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Kuis3 : AppCompatActivity() {

    private var skor = 0
    private var sudahMenjawab = false
    private val POIN_PER_BENAR = 4
    private val DELAY_DURATION = 1500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kuis3)

        skor = intent.getIntExtra("SKOR", 0)

        val btnJawab1 = findViewById<Button>(R.id.btnJawab1)
        val btnJawab2 = findViewById<Button>(R.id.btnJawab2)
        val btnJawab3 = findViewById<Button>(R.id.btnJawab3)
        val btnJawab4 = findViewById<Button>(R.id.btnJawab4)

        // Jawaban Salah
        btnJawab1.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                Toast.makeText(this, "❌ Salah! Jawaban: PENSIL", Toast.LENGTH_SHORT).show()
                pindahKeKuis4(btnJawab1, btnJawab2, btnJawab3, btnJawab4)
            }
        }

        // Jawaban Benar (PENSIL)
        btnJawab2.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                skor += POIN_PER_BENAR
                Toast.makeText(this, "🎉 Benar! +$POIN_PER_BENAR poin", Toast.LENGTH_SHORT).show()
                pindahKeKuis4(btnJawab1, btnJawab2, btnJawab3, btnJawab4)
            }
        }

        // Jawaban Salah
        btnJawab3.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                Toast.makeText(this, "❌ Salah! Jawaban: PENSIL", Toast.LENGTH_SHORT).show()
                pindahKeKuis4(btnJawab1, btnJawab2, btnJawab3, btnJawab4)
            }
        }

        // Jawaban Salah
        btnJawab4.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                Toast.makeText(this, "❌ Salah! Jawaban: PENSIL", Toast.LENGTH_SHORT).show()
                pindahKeKuis4(btnJawab1, btnJawab2, btnJawab3, btnJawab4)
            }
        }
    }

    private fun pindahKeKuis4(vararg buttons: Button) {
        disableButtons(*buttons)
        buttons[1].setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
        for (i in buttons.indices) {
            if (i != 1) {
                buttons[i].setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, Kuis4::class.java)
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