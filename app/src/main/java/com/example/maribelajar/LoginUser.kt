package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginUser : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnKembali: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginuser)

        etUsername = findViewById(R.id.etUsernameUser)
        btnLogin = findViewById(R.id.btnLoginUserSubmit)
        btnKembali = findViewById(R.id.btnKembaliUser)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()

            when {
                username.isEmpty() -> {
                    etUsername.error = "Username harus diisi"
                    etUsername.requestFocus()
                }
                username == "user" -> {
                    Toast.makeText(this, "Login User Berhasil! 🎉", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, BerandaActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else -> {
                    Toast.makeText(this, "Username tidak terdaftar!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnKembali.setOnClickListener {
            finish()
        }
    }
}