package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class KuisLvl3 : AppCompatActivity() {

    private var skor = 0
    private var sudahMenjawab = false
    private val POIN_PER_BENAR = 10
    private val DELAY_DURATION = 1500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kuislv3)

        skor = intent.getIntExtra("SKOR", 0)

        val btnA = findViewById<Button>(R.id.btnA)
        val btnB = findViewById<Button>(R.id.btnB)
        val btnC = findViewById<Button>(R.id.btnC)
        val btnD = findViewById<Button>(R.id.btnD)

        // Jawaban Salah
        btnA.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                Toast.makeText(this, "❌ Jawaban Salah!", Toast.LENGTH_SHORT).show()
                disableButtons(btnA, btnB, btnC, btnD)
                btnA.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))

                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, Kuis11::class.java)
                    intent.putExtra("SKOR", skor)
                    startActivity(intent)
                    finish()
                }, DELAY_DURATION)
            }
        }

        // Jawaban Benar - Dapat 10 POIN + LANGSUNG PINDAH
        btnB.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                skor += POIN_PER_BENAR

                Toast.makeText(
                    this,
                    "✅ Jawaban Benar! +$POIN_PER_BENAR poin",
                    Toast.LENGTH_SHORT
                ).show()

                disableButtons(btnA, btnB, btnC, btnD)
                btnB.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
                btnA.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
                btnC.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
                btnD.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))

                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, Kuis11::class.java)
                    intent.putExtra("SKOR", skor)
                    startActivity(intent)
                    finish()
                }, DELAY_DURATION)
            }
        }

        // Jawaban Salah
        btnC.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                Toast.makeText(this, "❌ Jawaban Salah!", Toast.LENGTH_SHORT).show()
                disableButtons(btnA, btnB, btnC, btnD)
                btnC.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))

                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, Kuis11::class.java)
                    intent.putExtra("SKOR", skor)
                    startActivity(intent)
                    finish()
                }, DELAY_DURATION)
            }
        }

        // Jawaban Salah
        btnD.setOnClickListener {
            if (!sudahMenjawab) {
                sudahMenjawab = true
                Toast.makeText(this, "❌ Jawaban Salah!", Toast.LENGTH_SHORT).show()
                disableButtons(btnA, btnB, btnC, btnD)
                btnD.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))

                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, Kuis11::class.java)
                    intent.putExtra("SKOR", skor)
                    startActivity(intent)
                    finish()
                }, DELAY_DURATION)
            }
        }
    }

    private fun disableButtons(vararg buttons: Button) {
        for (btn in buttons) {
            btn.isEnabled = false
        }
    }
}