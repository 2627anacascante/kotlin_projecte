package com.example.myapplication
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/** Holder d'una fila d'item_questionari.xml, igual que UsuariHolder. */
class QuestionariHolder(vista: View) : RecyclerView.ViewHolder(vista) {

    private val txtTitol: TextView = vista.findViewById(R.id.txtTitol)
    private val txtDetall: TextView = vista.findViewById(R.id.txtDetall)
    private val btnEsborrar: ImageButton = vista.findViewById(R.id.btnEsborrar)

    fun pinta(questionari: Questionari, alEditar: (Int) -> Unit, alEsborrar: (Int) -> Unit) {
        txtTitol.text = questionari.titol
        txtDetall.text =
            itemView.context.getString(R.string.detall_questionari, questionari.preguntes)

        // Toc a la fila = editar el qüestionari.
        itemView.setOnClickListener {
            val pos = bindingAdapterPosition
            if (pos != RecyclerView.NO_POSITION) alEditar(pos)
        }
        btnEsborrar.setOnClickListener {
            val pos = bindingAdapterPosition
            if (pos != RecyclerView.NO_POSITION) alEsborrar(pos)
        }
    }
}
