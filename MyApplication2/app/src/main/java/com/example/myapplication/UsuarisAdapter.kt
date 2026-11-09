package com.example.myapplication
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter: fa de pont entre la llista de dades i el RecyclerView.
 *  - onCreateViewHolder: crea (infla) una fila buida i li dona un Holder.
 *  - onBindViewHolder: omple una fila amb les dades de la posició indicada.
 *  - getItemCount: quantes files hi ha.
 * Els events (modificar/esborrar) no es resolen aquí: es passen al Fragment
 * com a funcions lambda, així l'Adapter no sap res de Toasts ni de pantalles.
 */
class UsuarisAdapter(
    private val usuaris: MutableList<Usuari>,
    private val alModificar: (Int) -> Unit,
    private val alEsborrar: (Int) -> Unit
) : RecyclerView.Adapter<UsuariHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usuari, parent, false)
        return UsuariHolder(vista)
    }

    override fun onBindViewHolder(holder: UsuariHolder, position: Int) {
        holder.pinta(usuaris[position], alModificar, alEsborrar)
    }

    override fun getItemCount(): Int = usuaris.size

    // Operacions sobre la llista. El notifyItem... avisa el RecyclerView
    // de QUINA fila ha canviat (així anima i només repinta aquesta).
    fun afegir(usuari: Usuari) {
        usuaris.add(usuari)
        notifyItemInserted(usuaris.lastIndex)
    }

    fun modificar(posicio: Int, usuari: Usuari) {
        usuaris[posicio] = usuari
        notifyItemChanged(posicio)
    }

    fun eliminar(posicio: Int) {
        usuaris.removeAt(posicio)
        notifyItemRemoved(posicio)
    }
}