package com.example.maribelajar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HasilKuis : AppCompatActivity() {

    private lateinit var txtNama: TextView
    private lateinit var txtKelas: TextView
    private lateinit var txtNilaiMembaca: TextView
    private lateinit var txtNilaiBerhitung: TextView
    private lateinit var txtTotal: TextView
    private lateinit var txtKategori: TextView
    private lateinit var txtBadge: TextView
    private lateinit var txtEmoji: TextView
    private lateinit var txtPerkembanganMembaca: TextView
    private lateinit var txtPerkembanganBerhitung: TextView
    private lateinit var txtStatusPerkembangan: TextView
    private lateinit var txtRekomendasi: TextView
    private lateinit var progressMembaca: ProgressBar
    private lateinit var progressBerhitung: ProgressBar
    private lateinit var progressPerkembanganMembaca: ProgressBar
    private lateinit var progressPerkembanganBerhitung: ProgressBar
    private lateinit var btnKembaliBeranda: Button  // ← Ganti nama
    private lateinit var btnUlangi: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hasilkuis)

        initViews()

        val nama = intent.getStringExtra("NAMA") ?: "Eci"
        val kelas = intent.getStringExtra("KELAS") ?: "1"
        val nilaiMembaca = intent.getIntExtra("NILAI_MEMBACA", 85)
        val nilaiBerhitung = intent.getIntExtra("NILAI_BERHITUNG", 75)
        val nilaiSebelumnyaMembaca = intent.getIntExtra("SEBELUM_MEMBACA", 75)
        val nilaiSebelumnyaBerhitung = intent.getIntExtra("SEBELUM_BERHITUNG", 70)

        tampilkanHasil(nama, kelas, nilaiMembaca, nilaiBerhitung, nilaiSebelumnyaMembaca, nilaiSebelumnyaBerhitung)
        setupListeners()
    }

    private fun initViews() {
        txtNama = findViewById(R.id.txtNama)
        txtKelas = findViewById(R.id.txtKelas)
        txtNilaiMembaca = findViewById(R.id.txtNilaiMembaca)
        txtNilaiBerhitung = findViewById(R.id.txtNilaiBerhitung)
        txtTotal = findViewById(R.id.txtTotal)
        txtKategori = findViewById(R.id.txtKategori)
        txtBadge = findViewById(R.id.txtBadge)
        txtEmoji = findViewById(R.id.txtEmoji)
        txtPerkembanganMembaca = findViewById(R.id.txtPerkembanganMembaca)
        txtPerkembanganBerhitung = findViewById(R.id.txtPerkembanganBerhitung)
        txtStatusPerkembangan = findViewById(R.id.txtStatusPerkembangan)
        txtRekomendasi = findViewById(R.id.txtRekomendasi)
        progressMembaca = findViewById(R.id.progressMembaca)
        progressBerhitung = findViewById(R.id.progressBerhitung)
        progressPerkembanganMembaca = findViewById(R.id.progressPerkembanganMembaca)
        progressPerkembanganBerhitung = findViewById(R.id.progressPerkembanganBerhitung)
        btnKembaliBeranda = findViewById(R.id.btnKembaliBeranda)  // ← Ganti ID
        btnUlangi = findViewById(R.id.btnUlangi)
    }

    private fun tampilkanHasil(
        nama: String,
        kelas: String,
        nilaiMembaca: Int,
        nilaiBerhitung: Int,
        nilaiSebelumnyaMembaca: Int,
        nilaiSebelumnyaBerhitung: Int
    ) {
        val total = (nilaiMembaca + nilaiBerhitung) / 2

        txtNama.text = nama
        txtKelas.text = "Kelas $kelas"
        txtNilaiMembaca.text = "$nilaiMembaca%"
        txtNilaiBerhitung.text = "$nilaiBerhitung%"
        txtTotal.text = "$total"

        progressMembaca.progress = nilaiMembaca
        progressBerhitung.progress = nilaiBerhitung
        progressPerkembanganMembaca.progress = nilaiMembaca
        progressPerkembanganBerhitung.progress = nilaiBerhitung

        val peningkatanMembaca = nilaiMembaca - nilaiSebelumnyaMembaca
        val peningkatanBerhitung = nilaiBerhitung - nilaiSebelumnyaBerhitung

        txtPerkembanganMembaca.text = when {
            peningkatanMembaca > 0 -> "📈 Meningkat ${peningkatanMembaca}%"
            peningkatanMembaca < 0 -> "📉 Menurun ${Math.abs(peningkatanMembaca)}%"
            else -> "➡️ Stabil"
        }

        txtPerkembanganBerhitung.text = when {
            peningkatanBerhitung > 0 -> "📈 Meningkat ${peningkatanBerhitung}%"
            peningkatanBerhitung < 0 -> "📉 Menurun ${Math.abs(peningkatanBerhitung)}%"
            else -> "➡️ Stabil"
        }

        when {
            total >= 90 -> {
                txtKategori.text = "🌟 Sangat Hebat!"
                txtBadge.text = "🏆 Juara Belajar"
                txtEmoji.text = "🎉🌟🎊"
                txtStatusPerkembangan.text = "✅ Perkembangan Luar Biasa!"
                txtRekomendasi.text = "Pertahankan Prestasimu! 🌟"
            }
            total >= 75 -> {
                txtKategori.text = "👍 Hebat!"
                txtBadge.text = "⭐ Hebat Belajar"
                txtEmoji.text = "😊👍"
                txtStatusPerkembangan.text = "✅ Perkembangan Baik"
                txtRekomendasi.text = "Terus Belajar dan Tingkatkan! 📚"
            }
            total >= 60 -> {
                txtKategori.text = "📖 Baik!"
                txtBadge.text = "📚 Rajin Belajar"
                txtEmoji.text = "📖💪"
                txtStatusPerkembangan.text = "📈 Perkembangan Cukup Baik"
                txtRekomendasi.text = "Perbanyak Latihan! 💪"
            }
            total >= 40 -> {
                txtKategori.text = "💪 Terus Belajar!"
                txtBadge.text = "💪 Semangat Belajar"
                txtEmoji.text = "💪😊"
                txtStatusPerkembangan.text = "📝 Masih Perlu Belajar"
                txtRekomendasi.text = "Jangan Menyerah, Terus Belajar! 💪"
            }
            else -> {
                txtKategori.text = "📝 Perlu Belajar Lagi!"
                txtBadge.text = "📝 Belajar Lagi Yuk!"
                txtEmoji.text = "📚🤗"
                txtStatusPerkembangan.text = "⚠️ Perlu Bimbingan"
                txtRekomendasi.text = "Belajar Lebih Giat Lagi! 📚"
            }
        }
    }

    private fun setupListeners() {
        // TOMBOL KEMBALI KE BERANDA
        btnKembaliBeranda.setOnClickListener {
            val intent = Intent(this, BerandaActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        // TOMBOL ULANGI KUIS
        btnUlangi.setOnClickListener {
            val intent = Intent(this, KuisLvl1::class.java)
            startActivity(intent)
            finish()
        }
    }
}