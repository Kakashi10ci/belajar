package com.example.maribelajar

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class BelajarAngka : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.belajarangka)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)
        val btn10 = findViewById<Button>(R.id.btn10)

        val btnKembali = findViewById<Button>(R.id.btnKembali)
        val btnSelanjutnya = findViewById<Button>(R.id.btnSelanjutnya)

        // Tombol Angka dengan animasi pop up dan suara
        btn1.setOnClickListener { playSoundAndAnimate(btn1, R.raw.satu) }
        btn2.setOnClickListener { playSoundAndAnimate(btn2, R.raw.dua) }
        btn3.setOnClickListener { playSoundAndAnimate(btn3, R.raw.tiga) }
        btn4.setOnClickListener { playSoundAndAnimate(btn4, R.raw.empat) }
        btn5.setOnClickListener { playSoundAndAnimate(btn5, R.raw.lima) }
        btn6.setOnClickListener { playSoundAndAnimate(btn6, R.raw.enam) }
        btn7.setOnClickListener { playSoundAndAnimate(btn7, R.raw.tujuh) }
        btn8.setOnClickListener { playSoundAndAnimate(btn8, R.raw.delapan) }
        btn9.setOnClickListener { playSoundAndAnimate(btn9, R.raw.sembilan) }
        btn10.setOnClickListener { playSoundAndAnimate(btn10, R.raw.sepuluh) }

        // Tombol Kembali
        btnKembali.setOnClickListener {
            finish()
        }

        // Tombol Selanjutnya
        btnSelanjutnya.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    BelajarAngka2::class.java
                )
            )
        }
    }

    private fun playSoundAndAnimate(button: Button, soundResId: Int) {
        // Memutar suara
        playSound(soundResId)

        // Animasi membesar (scale up) dan mengecil (scale down)
        animatePopUp(button)
    }

    private fun animatePopUp(view: View) {
        // Animasi membesar dari ukuran normal ke 1.2x
        val scaleUpX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1.0f, 1.2f)
        val scaleUpY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.0f, 1.2f)

        // Animasi mengecil kembali ke ukuran normal
        val scaleDownX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1.2f, 1.0f)
        val scaleDownY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.2f, 1.0f)

        // Gabungkan animasi scale up dan scale down secara berurutan
        scaleUpX.duration = 150 // 150ms untuk membesar
        scaleUpY.duration = 150
        scaleDownX.duration = 150 // 150ms untuk mengecil
        scaleDownY.duration = 150
        scaleDownX.startDelay = 150 // Mulai setelah scale up selesai
        scaleDownY.startDelay = 150

        // Interpolator untuk animasi yang lebih halus
        val interpolator = AccelerateDecelerateInterpolator()
        scaleUpX.interpolator = interpolator
        scaleUpY.interpolator = interpolator
        scaleDownX.interpolator = interpolator
        scaleDownY.interpolator = interpolator

        // Jalankan animasi scale up terlebih dahulu
        scaleUpX.start()
        scaleUpY.start()

        // Setelah scale up selesai, jalankan scale down
        scaleUpX.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                scaleDownX.start()
                scaleDownY.start()
            }
        })
    }

    private fun playSound(soundResId: Int) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(this, soundResId)
        mediaPlayer?.start()
        mediaPlayer?.setOnCompletionListener {
            it.release()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}