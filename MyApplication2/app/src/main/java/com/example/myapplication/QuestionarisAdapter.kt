package com.example.myapplication
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/** Adapter de la llista de qüestionaris, igual que UsuarisAdapter. */
class QuestionarisAdapter(
    private val questionaris: MutableList<Questionari>,
    private val alEditar: (Int) -> Unit,
    private val alEsborrar: (Int) -> Unit
) : RecyclerView.Adapter<QuestionariHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionariHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_questionari, parent, false)
        return QuestionariHolder(vista)
    }

    override fun onBindViewHolder(holder: QuestionariHolder, position: Int) {
        holder.pinta(questionaris[position], alEditar, alEsborrar)
    }

    override fun getItemCount(): Int = questionaris.size

    fun eliminar(posicio: Int) {
        questionaris.removeAt(posicio)
        notifyItemRemoved(posicio)
    }
}
