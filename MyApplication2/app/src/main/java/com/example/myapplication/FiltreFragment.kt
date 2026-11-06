package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class FiltreFragment : Fragment(R.layout.fragment_filtre) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // De moment no filtra: només porta a la llista d'usuaris
        view.findViewById<Button>(R.id.btnAplicar).setOnClickListener {
            //navegarA(UsuarisFragment())
        }

        //configurarBarraInferior(view, R.id.nav_filtre)
    }
}
