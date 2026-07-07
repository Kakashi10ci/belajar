package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginAdmin : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnKembali: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginadmin)

        etUsername = findViewById(R.id.etUsernameAdmin)
        etPassword = findViewById(R.id.etPasswordAdmin)
        btnLogin = findViewById(R.id.btnLoginAdminSubmit)
        btnKembali = findViewById(R.id.btnKembaliAdmin)

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
                    Toast.makeText(this, "Login Admin Berhasil! 🎉", Toast.LENGTH_SHORT).show()

                    // LANGSUNG KE ADMIN DASHBOARD
                    val intent = Intent(this, AdminDashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else -> {
                    Toast.makeText(this, "Username atau Password Salah!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnKembali.setOnClickListener {
            finish()
        }
    }
}