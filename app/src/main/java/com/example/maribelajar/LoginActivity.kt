package com.example.maribelajar

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lamanlogin)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            when {
                username.isEmpty() -> {
                    etUsername.error = "Username harus diisi"
                    etUsername.requestFocus()
                }
                password.isEmpty() -> {
                    etPassword.error = "Password harus diisi"
                    etPassword.requestFocus()
                }
                username == "admin" && password == "1234" -> {
                    Toast.makeText(
                        this,
                        "Login Berhasil! 🎉",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Pindah ke Beranda atau halaman lain
                    val intent = Intent(this, KuisLvl1::class.java)
                    startActivity(intent)
                    finish()
                }
                else -> {
                    Toast.makeText(
                        this,
                        "Username atau Password Salah!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}