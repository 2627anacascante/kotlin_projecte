package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class DadesPersonalsFragment : Fragment(R.layout.fragment_dades_personals) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarFletxaEnrere(view)

        view.findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            tornarEnrere()
        }
    }
}
