package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MembacaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.membaca)

        val btnHuruf = findViewById<Button>(R.id.btnHuruf)

        btnHuruf.setOnClickListener {
            val intent = Intent(this, HurufActivity::class.java)
            startActivity(intent)
        }
    }
}