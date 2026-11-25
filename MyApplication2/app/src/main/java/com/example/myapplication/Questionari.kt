package com.example.myapplication

/** Model de dades d'una fila de la llista de qüestionaris. */
data class Questionari(val titol: String, val preguntes: Int)

/**
 * Dades de prova en memòria, igual que RepositoriUsuaris: viuen aquí perquè
 * cada cop que naveguem es crea un QuestionarisFragment nou.
 */
object RepositoriQuestionaris {
    val questionaris = mutableListOf(
        Questionari("Qüestionari ESO", 8),
        Questionari("Qüestionari Batxillerat", 10),
        Questionari("Qüestionari FP", 6)
    )
}
