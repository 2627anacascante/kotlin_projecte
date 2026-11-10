package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UsuariFragment : Fragment(R.layout.fragment_usuaris) {

    private lateinit var adapter: UsuarisAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Llista: l'Adapter rep les dades i les funcions que atenen els events
        adapter = UsuarisAdapter(RepositoriUsuaris.usuaris, ::modificarUsuari, ::esborrarUsuari)
        view.findViewById<RecyclerView>(R.id.llista).adapter = adapter

        // Barra superior: + afegeix un usuari de prova, ⋮ obre el menú
        view.findViewById<MaterialToolbar>(R.id.barra).setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.accio_afegir -> afegirUsuari()
                R.id.accio_menu -> navegarA(MenuFragment())
            }
            true
        }

        configurarBarraInferior(view, R.id.nav_usuaris)
    }

    private fun afegirUsuari() {
        val nou =
            Usuari(getString(R.string.usuari_nou, adapter.itemCount + 1), "ESO", "Educació", 16)
        adapter.afegir(nou)
        avisa(R.string.toast_afegit, nou.nom)
    }

    // De moment "modificar" simula un canvi (puja 1 any l'edat).
    // Més endavant obrirà UsuariFragment amb les dades de l'usuari.
    private fun modificarUsuari(posicio: Int) {
        val editat = RepositoriUsuaris.usuaris[posicio].let { it.copy(edat = it.edat + 1) }
        adapter.modificar(posicio, editat)
        avisa(R.string.toast_modificat, editat.nom)
    }

    private fun esborrarUsuari(posicio: Int) {
        val usuari = RepositoriUsuaris.usuaris[posicio]
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.dialeg_titol)
            .setMessage(R.string.dialeg_missatge)
            .setNegativeButton(R.string.cancellar, null)
            .setPositiveButton(R.string.esborrar) { _, _ ->
                adapter.eliminar(posicio)
                avisa(R.string.toast_eliminat, usuari.nom)
            }
            .show()
    }

    /** Mostra un Toast i escriu el mateix missatge al Logcat (filtra per "Usuaris"). */
    private fun avisa(missatge: Int, nom: String) {
        val text = getString(missatge, nom)
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
        Log.d("Usuaris", text)
    }
}