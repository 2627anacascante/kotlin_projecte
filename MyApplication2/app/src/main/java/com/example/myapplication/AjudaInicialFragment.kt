package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class AjudaInicialFragment : Fragment(R.layout.fragment_ajuda_inicial) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnEntesos).setOnClickListener {
            navegarAPrincipal(UsuarisFragment())
        }
    }
}
