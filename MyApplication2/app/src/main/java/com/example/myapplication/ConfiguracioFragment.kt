package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class ConfiguracioFragment : Fragment(R.layout.fragment_configuracio) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarFletxaEnrere(view)
        // Guardar les preferències es farà en una activitat posterior
    }
}
