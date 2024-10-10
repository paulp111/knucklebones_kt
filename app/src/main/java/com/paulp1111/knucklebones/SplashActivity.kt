package com.paulp1111.knucklebones

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private var hasNavigated = false // Flag to track navigation to MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val videoView: VideoView = findViewById(R.id.splashVideoView)
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.splash_video)

        videoView.setVideoURI(videoUri)
        videoView.start()

        // Listener to navigate to MainActivity after the video finishes
        videoView.setOnCompletionListener {
            navigateToMainActivity()
        }

        // Fallback: navigate to MainActivity after 5 seconds if the video fails
        Handler(Looper.getMainLooper()).postDelayed({
            if (!videoView.isPlaying) {
                navigateToMainActivity()
            }
        }, 5000) // 5-second fallback
    }

    // Method to navigate to MainActivity and ensure it only happens once
    private fun navigateToMainActivity() {
        if (!hasNavigated) {
            hasNavigated = true // Set the flag to true to prevent duplicate navigation
            startActivity(Intent(this, MainActivity::class.java))
            finish() // End SplashActivity
        }
    }
}
