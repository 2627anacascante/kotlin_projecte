package com.example.myapplication;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class LogoFragment : Fragment(R.layout.fragment_logo) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Tocar qualsevol lloc de la pantalla porta al login
        view.setOnClickListener { navegarA(LoginFragment()) }
        view.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, LoginFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
