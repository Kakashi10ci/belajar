package com.example.maribelajar

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var etCariSiswa: EditText
    private lateinit var btnCari: Button
    private lateinit var btnTampilkanSemua: Button
    private lateinit var btnListSiswa: Button
    private lateinit var btnLihatHasilKuis: Button
    private lateinit var btnKembali: Button
    private lateinit var cardHasil: CardView

    private lateinit var txtNamaSiswa: TextView
    private lateinit var txtKelasSiswa: TextView
    private lateinit var txtStatus: TextView
    private lateinit var txtPersenMembacaAdmin: TextView
    private lateinit var txtPersenBerhitungAdmin: TextView
    private lateinit var txtRataRataAdmin: TextView
    private lateinit var txtBadgeAdmin: TextView

    private lateinit var progressMembacaAdmin: ProgressBar
    private lateinit var progressBerhitungAdmin: ProgressBar

    private data class Siswa(
        val nama: String,
        val kelas: String,
        val status: String,
        val membaca: Int,
        val berhitung: Int,
        val badge: String
    )

    private val daftarSiswa = listOf(
        Siswa("Eci", "1", "Aktif", 85, 75, "🏆 Hebat Belajar"),
        Siswa("Budi", "1", "Aktif", 90, 80, "🏆 Juara Belajar"),
        Siswa("Ani", "2", "Aktif", 70, 85, "🏆 Rajin Belajar"),
        Siswa("Rudi", "2", "Aktif", 60, 65, "⭐ Semangat Belajar"),
        Siswa("Siti", "3", "Aktif", 95, 90, "🏆 Hebat Belajar"),
        Siswa("Anton", "3", "Aktif", 75, 70, "⭐ Semangat Belajar")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_dashboard)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        etCariSiswa = findViewById(R.id.etCariSiswa)
        btnCari = findViewById(R.id.btnCari)
        btnTampilkanSemua = findViewById(R.id.btnTampilkanSemua)
        btnListSiswa = findViewById(R.id.btnListSiswa)
        btnLihatHasilKuis = findViewById(R.id.btnLihatHasilKuis)
        btnKembali = findViewById(R.id.btnKembali)
        cardHasil = findViewById(R.id.cardHasil)

        txtNamaSiswa = findViewById(R.id.txtNamaSiswa)
        txtKelasSiswa = findViewById(R.id.txtKelasSiswa)
        txtStatus = findViewById(R.id.txtStatus)
        txtPersenMembacaAdmin = findViewById(R.id.txtPersenMembacaAdmin)
        txtPersenBerhitungAdmin = findViewById(R.id.txtPersenBerhitungAdmin)
        txtRataRataAdmin = findViewById(R.id.txtRataRataAdmin)
        txtBadgeAdmin = findViewById(R.id.txtBadgeAdmin)

        progressMembacaAdmin = findViewById(R.id.progressMembacaAdmin)
        progressBerhitungAdmin = findViewById(R.id.progressBerhitungAdmin)
    }

    private fun setupListeners() {
        btnCari.setOnClickListener {
            val keyword = etCariSiswa.text.toString().trim()
            if (keyword.isEmpty()) {
                Toast.makeText(this, "Masukkan nama siswa!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            cariSiswa(keyword)
        }

        btnTampilkanSemua.setOnClickListener {
            etCariSiswa.text.clear()
            tampilkanSemuaSiswa()
        }

        btnListSiswa.setOnClickListener {
            tampilkanListSiswa()
        }

        btnLihatHasilKuis.setOnClickListener {
            val intent = Intent(this, HasilKuis::class.java)
            intent.putExtra("NAMA", "Semua Siswa")
            intent.putExtra("KELAS", "-")
            intent.putExtra("NILAI_MEMBACA", 0)
            intent.putExtra("NILAI_BERHITUNG", 0)
            startActivity(intent)
        }

        btnKembali.setOnClickListener {
            finish()
        }
    }

    private fun cariSiswa(keyword: String) {
        val hasil = daftarSiswa.find {
            it.nama.equals(keyword, ignoreCase = true)
        }

        if (hasil != null) {
            tampilkanDataSiswa(hasil)
            cardHasil.visibility = View.VISIBLE
        } else {
            Toast.makeText(this, "Siswa '$keyword' tidak ditemukan!", Toast.LENGTH_LONG).show()
            cardHasil.visibility = View.GONE
        }
    }

    private fun tampilkanSemuaSiswa() {
        val items = daftarSiswa.map { "${it.nama} (Kelas ${it.kelas}) - Membaca: ${it.membaca}%, Berhitung: ${it.berhitung}%" }.toTypedArray()

        AlertDialog.Builder(this)
            .setTitle("📋 Daftar Semua Siswa")
            .setItems(items) { dialog, which ->
                val siswa = daftarSiswa[which]
                tampilkanDataSiswa(siswa)
                cardHasil.visibility = View.VISIBLE
                etCariSiswa.setText(siswa.nama)
                dialog.dismiss()
            }
            .setPositiveButton("Tutup", null)
            .show()

        if (daftarSiswa.isNotEmpty()) {
            tampilkanDataSiswa(daftarSiswa.first())
            cardHasil.visibility = View.VISIBLE
        }
    }

    private fun tampilkanListSiswa() {
        val namaSiswa = daftarSiswa.map { it.nama }.toTypedArray()

        AlertDialog.Builder(this)
            .setTitle("📋 Daftar Nama Siswa")
            .setItems(namaSiswa) { dialog, which ->
                val siswa = daftarSiswa[which]
                tampilkanDataSiswa(siswa)
                cardHasil.visibility = View.VISIBLE
                etCariSiswa.setText(siswa.nama)
                dialog.dismiss()
            }
            .setPositiveButton("Tutup", null)
            .show()
    }

    private fun tampilkanDataSiswa(siswa: Siswa) {
        txtNamaSiswa.text = "👤 Nama: ${siswa.nama}"
        txtKelasSiswa.text = "📚 Kelas: ${siswa.kelas}"
        txtStatus.text = "✅ Status: ${siswa.status}"

        progressMembacaAdmin.progress = siswa.membaca
        txtPersenMembacaAdmin.text = "${siswa.membaca}%"

        progressBerhitungAdmin.progress = siswa.berhitung
        txtPersenBerhitungAdmin.text = "${siswa.berhitung}%"

        val rataRata = (siswa.membaca + siswa.berhitung) / 2
        txtRataRataAdmin.text = "📊 Nilai Rata-rata: $rataRata"

        txtBadgeAdmin.text = siswa.badge
    }
}