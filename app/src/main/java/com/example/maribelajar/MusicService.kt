package com.example.maribelajar

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class MusicService : Service() {

    companion object {
        private const val TAG = "MusicService"
    }

    private var mediaPlayer: MediaPlayer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: Service started")

        // Cek jika mediaPlayer sudah dibuat
        if (mediaPlayer == null) {
            try {
                // Pastikan file audio ada di res/raw/
                mediaPlayer = MediaPlayer.create(this, R.raw.backsound)

                if (mediaPlayer == null) {
                    Log.e(TAG, "onStartCommand: MediaPlayer creation failed!")
                    return START_STICKY
                }

                mediaPlayer?.apply {
                    isLooping = true  // Putar berulang
                    setVolume(0.5f, 0.5f)  // Volume 50%
                    start()
                    Log.d(TAG, "onStartCommand: Music started playing")
                }

                // Listener jika ada error
                mediaPlayer?.setOnErrorListener { _, what, extra ->
                    Log.e(TAG, "MediaPlayer error: what=$what, extra=$extra")
                    false
                }

            } catch (e: Exception) {
                Log.e(TAG, "onStartCommand: Error creating MediaPlayer", e)
            }
        } else {
            // Jika mediaPlayer sudah ada, lanjutkan pemutaran
            if (!mediaPlayer!!.isPlaying) {
                mediaPlayer?.start()
                Log.d(TAG, "onStartCommand: Music resumed")
            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: Service destroyed")
        mediaPlayer?.apply {
            if (isPlaying) {
                stop()
            }
            release()
        }
        mediaPlayer = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}