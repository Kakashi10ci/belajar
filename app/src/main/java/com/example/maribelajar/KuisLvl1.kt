package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class KuisLvl1 : AppCompatActivity() {

    companion object {
        private const val TAG = "KuisLvl1"
    }

    private var skor = 0
    private var sudahMenjawab = false
    private val POIN_PER_BENAR = 4
    private val DELAY_DURATION = 1500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            Log.d(TAG, "onCreate: Setting content view")
            setContentView(R.layout.kuislv1)
            Log.d(TAG, "onCreate: Content view set successfully")

            // Ambil skor dari Intent sebelumnya
            skor = intent.getIntExtra("SKOR", 0)

            val btnJawab1 = findViewById<Button>(R.id.btnJawab1)
            val btnJawab2 = findViewById<Button>(R.id.btnJawab2)
            val btnJawab3 = findViewById<Button>(R.id.btnJawab3)

            // Jawaban benar: Huruf A - Dapat 4 POIN
            btnJawab1.setOnClickListener {
                if (!sudahMenjawab) {
                    sudahMenjawab = true
                    skor += POIN_PER_BENAR

                    Toast.makeText(
                        this,
                        "🎉 Jawaban Benar! +$POIN_PER_BENAR poin",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Nonaktifkan semua tombol
                    disableButtons(btnJawab1, btnJawab2, btnJawab3)
                    btnJawab1.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
                    btnJawab2.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
                    btnJawab3.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))

                    // DELAY LALU PINDAH KE KUIS2
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, Kuis2::class.java)
                        intent.putExtra("SKOR", skor)
                        startActivity(intent)
                        finish()
                    }, DELAY_DURATION)
                }
            }

            // Jawaban salah - tetap lanjut ke halaman berikutnya
            btnJawab2.setOnClickListener {
                if (!sudahMenjawab) {
                    sudahMenjawab = true
                    Toast.makeText(
                        this,
                        "❌ Jawaban Salah! Jawaban benar adalah A",
                        Toast.LENGTH_SHORT
                    ).show()

                    disableButtons(btnJawab1, btnJawab2, btnJawab3)
                    btnJawab1.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
                    btnJawab2.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
                    btnJawab3.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))

                    // DELAY LALU PINDAH KE KUIS2 (TETAP LANJUT)
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, Kuis2::class.java)
                        intent.putExtra("SKOR", skor)
                        startActivity(intent)
                        finish()
                    }, DELAY_DURATION)
                }
            }

            btnJawab3.setOnClickListener {
                if (!sudahMenjawab) {
                    sudahMenjawab = true
                    Toast.makeText(
                        this,
                        "❌ Jawaban Salah! Jawaban benar adalah A",
                        Toast.LENGTH_SHORT
                    ).show()

                    disableButtons(btnJawab1, btnJawab2, btnJawab3)
                    btnJawab1.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
                    btnJawab2.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
                    btnJawab3.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))

                    // DELAY LALU PINDAH KE KUIS2 (TETAP LANJUT)
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, Kuis2::class.java)
                        intent.putExtra("SKOR", skor)
                        startActivity(intent)
                        finish()
                    }, DELAY_DURATION)
                }
            }

        } catch (e: Exception) {
            Log.e(TAG, "onCreate error: ${e.message}", e)
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun disableButtons(vararg buttons: Button) {
        for (btn in buttons) {
            btn.isEnabled = false
        }
    }
}