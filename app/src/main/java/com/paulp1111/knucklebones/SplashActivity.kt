package com.paulp1111.knucklebones

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private var hasNavigated = false // to track navigation to MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val videoView: VideoView = findViewById(R.id.splashVideoView)
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.splash_video)

        videoView.setVideoURI(videoUri)
        videoView.start()

        //navigate to MainActivity after video finishes
        videoView.setOnCompletionListener {
            navigateToMainActivity()
        }

        // Fallback after 5 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            if (!videoView.isPlaying) {
                navigateToMainActivity()
            }
        }, 5000) //
    }

    // nav to MainActivitiy only once
    private fun navigateToMainActivity() {
        if (!hasNavigated) {
            hasNavigated = true // prevent duplicate
            startActivity(Intent(this, MainActivity::class.java))
            finish() // End
        }
    }
}
