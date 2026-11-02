package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MenuProvesFragment : Fragment(R.layout.fragment_menu_proves) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Cada botó carrega un fragment al contenidor
        obrir(view, R.id.btnLogo, LogoFragment())
        obrir(view, R.id.btnLogin, LoginFragment())
        obrir(view, R.id.btnRegistre, RegistreFragment())
        obrir(view, R.id.btnAjudaInicial, AjudaInicialFragment())
        obrir(view, R.id.btnUsuaris, UsuarisFragment())
        obrir(view, R.id.btnUsuari, UsuariFragment())
        obrir(view, R.id.btnQuestionaris, QuestionarisFragment())
        obrir(view, R.id.btnCrearQuestionari, CrearQuestionariFragment())
        obrir(view, R.id.btnFiltre, FiltreFragment())
        obrir(view, R.id.btnMenu, MenuFragment())
        obrir(view, R.id.btnConfiguracio, ConfiguracioFragment())
        obrir(view, R.id.btnDades, DadesPersonalsFragment())
        obrir(view, R.id.btnAjuda, AjudaFragment())

        // Diàleg de confirmació d'esborrar
        view.findViewById<Button>(R.id.btnDialeg).setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.dialeg_titol)
                .setMessage(R.string.dialeg_missatge)
                .setNegativeButton(R.string.cancellar, null)
                .setPositiveButton(R.string.esborrar, null)
                .show()
        }
    }

    // Substitueix el fragment actual i el guarda a la pila (backstack),
    // de manera que el botó enrere torna al menú.
    private fun obrir(view: View, idBoto: Int, fragment: Fragment) {
        view.findViewById<Button>(idBoto).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
//mètode de la classe FragmentTransaction d'Android que optimitza i permet el reordenament visual
// i del cicle de vida de les operacions de fragments que formen part d'una mateixa transacció (com replace, add, remove o operacions amb el backstack).
                .replace(R.id.fragmentContainerView, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
