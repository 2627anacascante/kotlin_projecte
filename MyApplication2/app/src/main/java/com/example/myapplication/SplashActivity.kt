package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Pantalla de benvinguda (splash). És l'Activity d'inici de l'app (LAUNCHER).
 * Anima el logotip (fade + escala amb rebot), després el nom i l'eslogan,
 * i en acabar obre MainActivity i es tanca perquè el botó enrere no hi torni.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.imgLogo)
        val nom = findViewById<TextView>(R.id.txtNomApp)
        val eslogan = findViewById<TextView>(R.id.txtEslogan)

        // Estat inicial de l'animació
        logo.alpha = 0f
        logo.scaleX = 0.3f
        logo.scaleY = 0.3f
        nom.alpha = 0f
        eslogan.alpha = 0f

        // 1) Logo: apareix i creix amb efecte de rebot
        logo.animate()
            .alpha(1f).scaleX(1f).scaleY(1f)
            .setDuration(900)
            .setInterpolator(OvershootInterpolator())
            .start()

        // 2) Nom de l'app, amb retard
        nom.animate().alpha(1f).setStartDelay(700).setDuration(500).start()

        // 3) Eslogan; quan acaba, esperem un moment i obrim l'app
        eslogan.animate()
            .alpha(1f).setStartDelay(1100).setDuration(500)
            .withEndAction { eslogan.postDelayed({ obrirApp() }, 800) }
            .start()
    }

    private fun obrirApp() {
        if (isFinishing) return
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}