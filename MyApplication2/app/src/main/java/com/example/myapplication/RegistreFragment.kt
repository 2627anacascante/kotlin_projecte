package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class RegistreFragment : Fragment(R.layout.fragment_registre) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // En crear el compte, la primera vegada es mostra l'ajuda inicial
        view.findViewById<Button>(R.id.btnCrearCompte).setOnClickListener {
            navegarA(AjudaInicialFragment())
        }
        view.findViewById<Button>(R.id.btnJaTens).setOnClickListener {
            navegarA(LoginFragment())
        }
    }
}
