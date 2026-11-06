package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class AjudaFragment : Fragment(R.layout.fragment_ajuda) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //configurarFletxaEnrere(view)

        view.findViewById<Button>(R.id.btnVeureAjuda).setOnClickListener {
            //navegarA(AjudaInicialFragment())
        }
    }
}
