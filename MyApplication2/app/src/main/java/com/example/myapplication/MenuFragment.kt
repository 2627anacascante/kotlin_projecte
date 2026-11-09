package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MenuFragment : Fragment(R.layout.fragment_menu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarFletxaEnrere(view)

        view.findViewById<Button>(R.id.btnConfiguracio).setOnClickListener {
            navegarA(ConfiguracioFragment())
        }
        view.findViewById<Button>(R.id.btnDades).setOnClickListener {
            navegarA(DadesPersonalsFragment())
        }
        view.findViewById<Button>(R.id.btnAjuda).setOnClickListener {
            navegarA(AjudaFragment())
        }
        // Tancar sessió: buida tota la pila i va al login
        view.findViewById<Button>(R.id.btnTancarSessio).setOnClickListener {
            navegarAPrincipal(LoginFragment())
        }
    }
}
