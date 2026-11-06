package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class CrearQuestionariFragment : Fragment(R.layout.fragment_crear_questionari) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //configurarFletxaEnrere(view)

        view.findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            //tornarEnrere()
        }
    }
}
