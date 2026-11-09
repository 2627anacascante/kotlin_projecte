package com.example.myapplication

/** Model de dades d'una fila de la llista. */
data class Usuari(val nom: String, val estudis: String, val ambit: String, val edat: Int)

/**
 * Dades de prova en memòria. Viuen aquí (i no al Fragment) perquè cada cop que
 * naveguem es crea un UsuarisFragment nou i la llista es perdria.
 * Més endavant es canviarà per una base de dades.
 */
object RepositoriUsuaris {
    val usuaris = mutableListOf(
        Usuari("Pep", "FP", "Tecnologia", 27),
        Usuari("Marta", "Batxillerat", "Salut", 17),
        Usuari("Joan", "ESO", "Art i disseny", 15),
        Usuari("Laia", "Grau", "Educació", 22),
        Usuari("Nil", "FP", "Enginyeria", 19),
        Usuari("Aina", "Batxillerat", "Informàtica", 18)
    )
}