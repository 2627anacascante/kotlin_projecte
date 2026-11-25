package com.example.myapplication

/** MODEL — Dades d'un compte d'usuari. */
data class Compte(
    val usuari: String,
    val contrasenya: String,
    val nom: String,
    val cognoms: String,
    val telefon: String,
    val email: String
)

/**
 * MODEL — Magatzem de comptes en memòria (només n'hi ha un a tota l'app).
 * Té un compte de prova per poder fer login sense registrar-se.
 * Més endavant es podria canviar per una base de dades.
 */
object RepositoriComptes {

    private val comptes = mutableListOf(
        Compte("admin", "admin1234", "Admin", "OrientaApp", "600000000", "admin@orientaapp.cat")
    )

    fun existeix(usuari: String): Boolean =
        comptes.any { it.usuari == usuari }

    fun credencialsCorrectes(usuari: String, contrasenya: String): Boolean =
        comptes.any { it.usuari == usuari && it.contrasenya == contrasenya }

    fun afegeix(compte: Compte) {
        comptes.add(compte)
    }
}
