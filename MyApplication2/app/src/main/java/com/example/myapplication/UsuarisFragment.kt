package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar

class UsuarisFragment : Fragment(R.layout.fragment_usuaris) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Icones de la barra superior: + i ⋮
        view.findViewById<MaterialToolbar>(R.id.barra).setOnMenuItemClickListener { item ->
            when (item.itemId) {
                //R.id.accio_afegir -> navegarA(UsuariFragment())
                //R.id.accio_menu -> navegarA(MenuFragment())
            }
            true
        }

        //configurarBarraInferior(view, R.id.nav_usuaris)
    }
}
