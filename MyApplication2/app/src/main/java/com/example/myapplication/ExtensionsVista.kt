package com.example.myapplication

import com.google.android.material.textfield.TextInputLayout

/* VIEW — Funcions d'extensió per als camps de text de Material. */

/** Retorna el text escrit dins la caixa, o "" si està buida. */
fun TextInputLayout.textEscrit(): String =
    editText?.text?.toString() ?: ""

/** Mostra el missatge d'error (id d'un string) o l'amaga si és null. */
fun TextInputLayout.mostraError(idMissatge: Int?) {
    error = if (idMissatge == null) null else context.getString(idMissatge)
}
