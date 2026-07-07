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

class KataActivity3 : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kata3)

        val btnBuku = findViewById<Button>(R.id.btnBuku)
        val btnPensil = findViewById<Button>(R.id.btnPensil)
        val btnPensilWarna = findViewById<Button>(R.id.btnPensilwarna)
        val btnPenghapus = findViewById<Button>(R.id.btnPenghapus)
        val btnPenggaris = findViewById<Button>(R.id.btnPenggaris)
        val btnGunting = findViewById<Button>(R.id.btnGunting)

        val btnKembali = findViewById<Button>(R.id.btnKembali)
        val btnSelanjutnya = findViewById<Button>(R.id.btnSelanjutnya)

        // Tombol Kata dengan animasi pop up dan suara
        btnBuku.setOnClickListener {
            playSoundAndAnimate(btnBuku, R.raw.buku)
        }

        btnPensil.setOnClickListener {
            playSoundAndAnimate(btnPensil, R.raw.pensil)
        }

        btnPensilWarna.setOnClickListener {
            playSoundAndAnimate(btnPensilWarna, R.raw.pensilwarna)
        }

        btnPenghapus.setOnClickListener {
            playSoundAndAnimate(btnPenghapus, R.raw.penghapus)
        }

        btnPenggaris.setOnClickListener {
            playSoundAndAnimate(btnPenggaris, R.raw.penggaris)
        }

        btnGunting.setOnClickListener {
            playSoundAndAnimate(btnGunting, R.raw.gunting)
        }

        // Tombol Kembali
        btnKembali.setOnClickListener {
            finish()
        }

        // Tombol Selanjutnya
        btnSelanjutnya.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    BerandaActivity::class.java
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