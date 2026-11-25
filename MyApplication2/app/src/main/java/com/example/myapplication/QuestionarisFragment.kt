package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class QuestionarisFragment : Fragment(R.layout.fragment_questionaris) {

    private lateinit var adapter: QuestionarisAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Llista: l'Adapter rep les dades i les funcions que atenen els events
        adapter = QuestionarisAdapter(
            RepositoriQuestionaris.questionaris,
            ::editarQuestionari,
            ::esborrarQuestionari
        )
        view.findViewById<RecyclerView>(R.id.llista).adapter = adapter

        // Barra superior: + crea un qüestionari nou, ⋮ obre el menú d'opcions
        val barra = view.findViewById<MaterialToolbar>(R.id.barra)
        barra.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.accio_afegir -> navegarA(CrearQuestionariFragment())
                R.id.accio_menu -> mostrarMenuPrincipal(barra)
            }
            true
        }

        configurarBarraInferior(view, R.id.nav_questionaris)
    }

    private fun editarQuestionari(posicio: Int) {
        navegarA(EditarQuestionariFragment())
    }

    private fun esborrarQuestionari(posicio: Int) {
        val questionari = RepositoriQuestionaris.questionaris[posicio]
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.dialeg_titol_questionari)
            .setMessage(R.string.dialeg_missatge)
            .setNegativeButton(R.string.cancellar, null)
            .setPositiveButton(R.string.esborrar) { _, _ ->
                adapter.eliminar(posicio)
                avisa(R.string.toast_eliminat, questionari.titol)
            }
            .show()
    }

    /** Mostra un Toast i escriu el mateix missatge al Logcat (filtra per "Questionaris"). */
    private fun avisa(missatge: Int, nom: String) {
        val text = getString(missatge, nom)
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
        Log.d("Questionaris", text)
    }
}
