package com.example.myapplication

import android.util.Patterns

/*
 * MODEL — Regles de validació.
 * Funcions d'extensió sobre String: afegeixen "poders" nous a la classe String
 * sense modificar-la. Així qualsevol text pot fer text.esEmailValid().
 */

/** Usuari: mínim 3 caràcters i cap espai. */
fun String.esUsuariValid(): Boolean =
    length >= 3 && none { it.isWhitespace() }

/** Email: format nom@domini.ext (comprovador oficial d'Android). */
fun String.esEmailValid(): Boolean =
    Patterns.EMAIL_ADDRESS.matcher(this).matches()

/** Contrasenya: mínim 8 caràcters, amb almenys una lletra i un número. */
fun String.esContrasenyaValida(): Boolean =
    length >= 8 && any { it.isLetter() } && any { it.isDigit() }

/** Telèfon: exactament 9 xifres. */
fun String.esTelefonValid(): Boolean =
    length == 9 && all { it.isDigit() }

/** Nom o cognoms: no buit i només lletres, espais, guions o apòstrofs. */
fun String.esNomValid(): Boolean =
    isNotBlank() && all { it.isLetter() || it == ' ' || it == '-' || it == '\'' }