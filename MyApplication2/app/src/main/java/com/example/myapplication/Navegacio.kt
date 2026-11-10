package com.example.myapplication
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

// Substitueix el fragment actual pel nou i el guarda a la pila (backstack)
fun Fragment.navegarA(fragment: Fragment) {
    parentFragmentManager.beginTransaction()
        .setReorderingAllowed(true)
    //setReorderingAllowed(true) millora el rendiment i evita problemes amb fragments que es reordenen incorrectament durant les transaccions.
    // Això és especialment útil quan es fan múltiples transaccions de fragments en una sola operació.
        .replace(R.id.fragmentContainerView, fragment)
        .addToBackStack(null)
        .commit()
}

// Torna a la pantalla anterior (igual que el botó enrere del mòbil)
fun Fragment.tornarEnrere() {
    parentFragmentManager.popBackStack()
}

// La fletxa ← de la barra superior torna enrere
fun Fragment.configurarFletxaEnrere(view: View) {
    view.findViewById<MaterialToolbar>(R.id.barra).setNavigationOnClickListener {
        tornarEnrere()
    }
}

// Barra inferior: marca la pantalla actual i navega a les altres
fun Fragment.configurarBarraInferior(view: View, idActual: Int) {
    val barra = view.findViewById<BottomNavigationView>(R.id.navInferior)
    barra.selectedItemId = idActual
    barra.setOnItemSelectedListener { item ->
        if (item.itemId != idActual) {
            when (item.itemId) {
                R.id.nav_questionaris -> navegarAPrincipal(QuestionarisFragment())
                R.id.nav_usuaris -> navegarAPrincipal(UsuarisFragment())
                R.id.nav_filtre -> navegarAPrincipal(FiltreFragment())
            }
        }
        true
    }
}

/** Destí de primer nivell: buida la pila i carrega el fragment (sense tornar enrere). */
fun Fragment.navegarAPrincipal(fragment: Fragment) {
    parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    parentFragmentManager.beginTransaction()
        .setReorderingAllowed(true)
        .replace(R.id.fragmentContainerView, fragment)
        .commit()
}
