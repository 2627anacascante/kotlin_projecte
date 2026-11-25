package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * VIEWMODEL del Registre.
 * Valida els 6 camps amb les regles del Model i, si tot és correcte,
 * guarda el compte nou al RepositoriComptes.
 */
class RegistreViewModel : ViewModel() {

    private val _errorUsuari = MutableLiveData<Int?>()
    val errorUsuari: LiveData<Int?> = _errorUsuari

    private val _errorNom = MutableLiveData<Int?>()
    val errorNom: LiveData<Int?> = _errorNom

    private val _errorCognoms = MutableLiveData<Int?>()
    val errorCognoms: LiveData<Int?> = _errorCognoms

    private val _errorContrasenya = MutableLiveData<Int?>()
    val errorContrasenya: LiveData<Int?> = _errorContrasenya

    private val _errorTelefon = MutableLiveData<Int?>()
    val errorTelefon: LiveData<Int?> = _errorTelefon

    private val _errorEmail = MutableLiveData<Int?>()
    val errorEmail: LiveData<Int?> = _errorEmail

    private val _registreCorrecte = MutableLiveData(false)
    val registreCorrecte: LiveData<Boolean> = _registreCorrecte

    /** El Fragment la crida quan es prem "Crear compte". */
    fun crearCompte(
        usuari: String, nom: String, cognoms: String,
        contrasenya: String, telefon: String, email: String
    ) {
        _errorUsuari.value = when {
            usuari.isBlank() -> R.string.error_obligatori
            !usuari.esUsuariValid() -> R.string.error_usuari
            RepositoriComptes.existeix(usuari) -> R.string.error_usuari_existeix
            else -> null
        }
        _errorNom.value = if (nom.esNomValid()) null else R.string.error_nom
        _errorCognoms.value = if (cognoms.esNomValid()) null else R.string.error_nom
        _errorContrasenya.value =
            if (contrasenya.esContrasenyaValida()) null else R.string.error_contrasenya
        _errorTelefon.value = if (telefon.esTelefonValid()) null else R.string.error_telefon
        _errorEmail.value = if (email.esEmailValid()) null else R.string.error_email

        // Només guardem si cap camp té error
        val totCorrecte = listOf(
            _errorUsuari, _errorNom, _errorCognoms,
            _errorContrasenya, _errorTelefon, _errorEmail
        ).all { it.value == null }

        if (totCorrecte) {
            RepositoriComptes.afegeix(
                Compte(usuari, contrasenya, nom, cognoms, telefon, email)
            )
            _registreCorrecte.value = true
        }
    }

    fun navegacioFeta() {
        _registreCorrecte.value = false
    }
}
