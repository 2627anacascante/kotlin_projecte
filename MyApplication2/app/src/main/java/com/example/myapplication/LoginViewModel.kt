package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * VIEWMODEL del Login.
 * Rep el que escriu l'usuari, ho valida amb el Model i publica el resultat en LiveData.
 * No coneix cap vista: el Fragment observa els LiveData i decideix com mostrar-ho.
 */
class LoginViewModel : ViewModel() {

    // Error del camp usuari: id d'un string, o null si està bé
    private val _errorUsuari = MutableLiveData<Int?>()
    val errorUsuari: LiveData<Int?> = _errorUsuari

    // Error del camp contrasenya
    private val _errorContrasenya = MutableLiveData<Int?>()
    val errorContrasenya: LiveData<Int?> = _errorContrasenya

    // true quan el login és correcte i cal navegar
    private val _loginCorrecte = MutableLiveData(false)
    val loginCorrecte: LiveData<Boolean> = _loginCorrecte

    /** El Fragment la crida quan es prem "Entrar". */
    fun entrar(usuari: String, contrasenya: String) {
        _errorUsuari.value = when {
            usuari.isBlank() -> R.string.error_obligatori
            !usuari.esUsuariValid() -> R.string.error_usuari
            else -> null
        }
        _errorContrasenya.value =
            if (contrasenya.isBlank()) R.string.error_obligatori else null

        // Si algun camp té error, no seguim
        if (_errorUsuari.value != null || _errorContrasenya.value != null) return

        // Preguntem al Model si l'usuari i la contrasenya coincideixen
        if (RepositoriComptes.credencialsCorrectes(usuari, contrasenya)) {
            _loginCorrecte.value = true
        } else {
            _errorContrasenya.value = R.string.error_credencials
        }
    }

    /** Es crida després de navegar, perquè no es torni a navegar en girar la pantalla. */
    fun navegacioFeta() {
        _loginCorrecte.value = false
    }
}
