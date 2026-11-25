package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar

/**
 * Pantalla d'editar un qüestionari existent. Reutilitza el mateix formulari
 * que CrearQuestionariFragment, només canvia el títol de la barra superior.
 */
class EditarQuestionariFragment : Fragment(R.layout.fragment_crear_questionari) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarFletxaEnrere(view)
        view.findViewById<MaterialToolbar>(R.id.barra).title = getString(R.string.titol_editar_questionari)

        view.findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            tornarEnrere()
        }
    }
}
