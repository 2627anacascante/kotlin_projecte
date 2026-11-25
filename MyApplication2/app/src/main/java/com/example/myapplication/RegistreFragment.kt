package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputLayout

/**
 * VIEW del Registre: envia les dades al ViewModel i mostra els errors que rep.
 */
class RegistreFragment : Fragment(R.layout.fragment_registre) {

    private val viewModel: RegistreViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tilUsuari = view.findViewById<TextInputLayout>(R.id.tilUsuari)
        val tilNom = view.findViewById<TextInputLayout>(R.id.tilNom)
        val tilCognoms = view.findViewById<TextInputLayout>(R.id.tilCognoms)
        val tilContrasenya = view.findViewById<TextInputLayout>(R.id.tilContrasenya)
        val tilTelefon = view.findViewById<TextInputLayout>(R.id.tilTelefon)
        val tilEmail = view.findViewById<TextInputLayout>(R.id.tilEmail)

        // --- OBSERVAR (ViewModel -> Vista) ---
        viewModel.errorUsuari.observe(viewLifecycleOwner) { tilUsuari.mostraError(it) }
        viewModel.errorNom.observe(viewLifecycleOwner) { tilNom.mostraError(it) }
        viewModel.errorCognoms.observe(viewLifecycleOwner) { tilCognoms.mostraError(it) }
        viewModel.errorContrasenya.observe(viewLifecycleOwner) { tilContrasenya.mostraError(it) }
        viewModel.errorTelefon.observe(viewLifecycleOwner) { tilTelefon.mostraError(it) }
        viewModel.errorEmail.observe(viewLifecycleOwner) { tilEmail.mostraError(it) }

        viewModel.registreCorrecte.observe(viewLifecycleOwner) { correcte ->
            if (correcte) {
                viewModel.navegacioFeta()
                Toast.makeText(requireContext(), R.string.compte_creat, Toast.LENGTH_SHORT).show()
                navegarA(AjudaInicialFragment())
            }
        }

        // --- AVISAR (Vista -> ViewModel) ---
        view.findViewById<Button>(R.id.btnCrearCompte).setOnClickListener {
            viewModel.crearCompte(
                usuari = tilUsuari.textEscrit().trim(),
                nom = tilNom.textEscrit().trim(),
                cognoms = tilCognoms.textEscrit().trim(),
                contrasenya = tilContrasenya.textEscrit(),
                telefon = tilTelefon.textEscrit().trim(),
                email = tilEmail.textEscrit().trim()
            )
        }
        view.findViewById<Button>(R.id.btnJaTens).setOnClickListener {
            tornarEnrere()
        }
    }
}

