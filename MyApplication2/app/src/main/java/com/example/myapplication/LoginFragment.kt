package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputLayout

/**
 * VIEW del Login. Només fa dues coses:
 *  1) Avisa el ViewModel quan l'usuari prem un botó.
 *  2) Observa els LiveData del ViewModel i pinta el que hi arriba.
 */
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tilUsuari = view.findViewById<TextInputLayout>(R.id.tilUsuari)
        val tilContrasenya = view.findViewById<TextInputLayout>(R.id.tilContrasenya)

        // --- OBSERVAR (ViewModel -> Vista) ---
        viewModel.errorUsuari.observe(viewLifecycleOwner) { error ->
            tilUsuari.mostraError(error)
        }
        viewModel.errorContrasenya.observe(viewLifecycleOwner) { error ->
            tilContrasenya.mostraError(error)
        }
        viewModel.loginCorrecte.observe(viewLifecycleOwner) { correcte ->
            if (correcte) {
                viewModel.navegacioFeta()
                navegarAPrincipal(UsuarisFragment())
            }
        }

        // --- AVISAR (Vista -> ViewModel) ---
        view.findViewById<Button>(R.id.btnEntrar).setOnClickListener {
            viewModel.entrar(tilUsuari.textEscrit().trim(), tilContrasenya.textEscrit())
        }
        view.findViewById<Button>(R.id.btnRegistre).setOnClickListener {
            navegarA(RegistreFragment())
        }
    }
}
