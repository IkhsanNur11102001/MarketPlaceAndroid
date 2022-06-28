package com.nur_ikhsan.marketplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.nur_ikhsan.marketplace.ui.Navigation.NavigationActivity

@Suppress("DEPRECATION")
class splash_screen : AppCompatActivity() {

    private val splash: Long = 2000

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

         supportActionBar?.hide()
        Handler().postDelayed({
            startActivity(Intent(this, NavigationActivity::class.java))
            finish()
        }, splash)



     }
}