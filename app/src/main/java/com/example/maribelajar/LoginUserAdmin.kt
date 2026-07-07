package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginUserAdmin : AppCompatActivity() {

    private lateinit var btnLoginUser: Button
    private lateinit var btnLoginAdmin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginuseradmin)

        // MULAI MUSIC SERVICE
        startMusicService()

        btnLoginUser = findViewById(R.id.btnLoginUser)
        btnLoginAdmin = findViewById(R.id.btnLoginAdmin)

        btnLoginUser.setOnClickListener {
            val intent = Intent(this, LoginUser::class.java)
            startActivity(intent)
        }

        btnLoginAdmin.setOnClickListener {
            val intent = Intent(this, LoginAdmin::class.java)
            startActivity(intent)
        }
    }

    private fun startMusicService() {
        try {
            val intent = Intent(this, MusicService::class.java)
            startService(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Jangan hentikan service di sini agar musik tetap berjalan
    }
}