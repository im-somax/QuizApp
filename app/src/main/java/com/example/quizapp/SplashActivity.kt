package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        val background = object : Thread(){
            override fun run() {
                try{
                    Thread.sleep(6000)

                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                }
                catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}