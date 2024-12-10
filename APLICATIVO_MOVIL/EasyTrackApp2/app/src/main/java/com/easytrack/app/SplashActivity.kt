package com.easytrack.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import java.util.TimerTask

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Configurar un temporizador para que el Splash Screen dure 2 segundos
        Timer().schedule(object : TimerTask() {
            override fun run() {
                // Iniciar la MainActivity
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish() // Cerrar la actividad del Splash Screen
            }
        }, 2000) // 2000 milisegundos = 2 segundos
    }
}