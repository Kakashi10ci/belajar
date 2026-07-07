package com.example.maribelajar

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.maribelajar.Penambahan

class BelajarAngka3 : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.belajarangka3)

        val btn21 = findViewById<Button>(R.id.btn21)
        val btn22 = findViewById<Button>(R.id.btn22)
        val btn23 = findViewById<Button>(R.id.btn23)
        val btn24 = findViewById<Button>(R.id.btn24)
        val btn25 = findViewById<Button>(R.id.btn25)
        val btn26 = findViewById<Button>(R.id.btn26)
        val btn27 = findViewById<Button>(R.id.btn27)
        val btn28 = findViewById<Button>(R.id.btn28)
        val btn29 = findViewById<Button>(R.id.btn29)
        val btn30 = findViewById<Button>(R.id.btn30)

        val btnKembali = findViewById<Button>(R.id.btnKembali)
        val btnSelanjutnya = findViewById<Button>(R.id.btnSelanjutnya)

        // Tombol Angka dengan animasi pop up dan suara
        btn21.setOnClickListener { playSoundAndAnimate(btn21, R.raw.duasatu) }
        btn22.setOnClickListener { playSoundAndAnimate(btn22, R.raw.duadua) }
        btn23.setOnClickListener { playSoundAndAnimate(btn23, R.raw.duatiga) }
        btn24.setOnClickListener { playSoundAndAnimate(btn24, R.raw.duaempat) }
        btn25.setOnClickListener { playSoundAndAnimate(btn25, R.raw.dualima) }
        btn26.setOnClickListener { playSoundAndAnimate(btn26, R.raw.duaenam) }
        btn27.setOnClickListener { playSoundAndAnimate(btn27, R.raw.duatujuh) }
        btn28.setOnClickListener { playSoundAndAnimate(btn28, R.raw.duadelapan) }
        btn29.setOnClickListener { playSoundAndAnimate(btn29, R.raw.duasembilan) }
        btn30.setOnClickListener { playSoundAndAnimate(btn30, R.raw.tigapuluh) }

        // Tombol Kembali
        btnKembali.setOnClickListener {
            finish()
        }

        // Tombol Selanjutnya
        btnSelanjutnya.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    Penambahan::class.java
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