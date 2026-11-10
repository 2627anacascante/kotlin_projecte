package com.example.myapplication
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Holder: guarda les referències a les vistes d'UNA fila (item_usuari.xml)
 * perquè no s'hagi de fer findViewById cada cop que es reutilitza la fila
 * en fer scroll (d'aquí el nom "Recycler").
 */
class UsuariHolder(vista: View) : RecyclerView.ViewHolder(vista) {

    private val txtTitol: TextView = vista.findViewById(R.id.txtTitol)
    private val txtDetall: TextView = vista.findViewById(R.id.txtDetall)
    private val btnEsborrar: ImageButton = vista.findViewById(R.id.btnEsborrar)

    /** Omple la fila amb les dades de l'usuari i connecta els events de toc. */
    fun pinta(usuari: Usuari, alModificar: (Int) -> Unit, alEsborrar: (Int) -> Unit) {
        txtTitol.text = usuari.nom
        txtDetall.text = itemView.context.getString(
            R.string.detall_usuari, usuari.estudis, usuari.ambit, usuari.edat
        )

        // Toc a la fila = modificar. Es pregunta la posició en el moment del toc
        // (no abans), perquè pot haver canviat si s'han afegit/eliminat files.
        itemView.setOnClickListener {
            val pos = bindingAdapterPosition
            if (pos != RecyclerView.NO_POSITION) alModificar(pos)
        }
        btnEsborrar.setOnClickListener {
            val pos = bindingAdapterPosition
            if (pos != RecyclerView.NO_POSITION) alEsborrar(pos)
        }
    }
}
//bindingAdapterPosition és la posició de l'item dins l'adapter.
// NO_POSITION indica que no hi ha cap posició vàlida (per exemple, si l'item ha estat eliminat).
//ReciclerView és un component que mostra una llista d'elements de manera eficient, reutilitzant les vistes quan es fa scroll.