package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar

class QuestionarisFragment : Fragment(R.layout.fragment_questionaris) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialToolbar>(R.id.barra).setOnMenuItemClickListener { item ->
            when (item.itemId) {
                //R.id.accio_afegir -> navegarA(CrearQuestionariFragment())
                //R.id.accio_menu -> navegarA(MenuFragment())
            }
            true
        }

        //configurarBarraInferior(view, R.id.nav_questionaris)
    }
}
