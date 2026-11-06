package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class UsuariFragment : Fragment(R.layout.fragment_usuari) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //configurarFletxaEnrere(view)

        view.findViewById<Button>(R.id.btnCrearQuestionari).setOnClickListener {
            //navegarA(CrearQuestionariFragment())
        }
        // De moment no guarda res: només torna a la llista
        view.findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            //tornarEnrere()
        }
    }
}
