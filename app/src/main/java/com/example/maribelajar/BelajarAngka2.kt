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

class BelajarAngka2 : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.belajarangka2)

        val btn11 = findViewById<Button>(R.id.btn11)
        val btn12 = findViewById<Button>(R.id.btn12)
        val btn13 = findViewById<Button>(R.id.btn13)
        val btn14 = findViewById<Button>(R.id.btn14)
        val btn15 = findViewById<Button>(R.id.btn15)
        val btn16 = findViewById<Button>(R.id.btn16)
        val btn17 = findViewById<Button>(R.id.btn17)
        val btn18 = findViewById<Button>(R.id.btn18)
        val btn19 = findViewById<Button>(R.id.btn19)
        val btn20 = findViewById<Button>(R.id.btn20)

        val btnKembali = findViewById<Button>(R.id.btnKembali)
        val btnSelanjutnya = findViewById<Button>(R.id.btnSelanjutnya)

        // Tombol Angka dengan animasi pop up dan suara
        btn11.setOnClickListener { playSoundAndAnimate(btn11, R.raw.sebelas) }
        btn12.setOnClickListener { playSoundAndAnimate(btn12, R.raw.duabelas) }
        btn13.setOnClickListener { playSoundAndAnimate(btn13, R.raw.tigabelas) }
        btn14.setOnClickListener { playSoundAndAnimate(btn14, R.raw.empatbelas) }
        btn15.setOnClickListener { playSoundAndAnimate(btn15, R.raw.limabelas) }
        btn16.setOnClickListener { playSoundAndAnimate(btn16, R.raw.enambelas) }
        btn17.setOnClickListener { playSoundAndAnimate(btn17, R.raw.tujuhbelas) }
        btn18.setOnClickListener { playSoundAndAnimate(btn18, R.raw.delapanbelas) }
        btn19.setOnClickListener { playSoundAndAnimate(btn19, R.raw.sembilanbelas) }
        btn20.setOnClickListener { playSoundAndAnimate(btn20, R.raw.duapuluh) }

        // Tombol Kembali
        btnKembali.setOnClickListener {
            finish()
        }

        // Tombol Selanjutnya
        btnSelanjutnya.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    BelajarAngka3::class.java
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