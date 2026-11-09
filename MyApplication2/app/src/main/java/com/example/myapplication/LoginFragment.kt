package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnEntrar).setOnClickListener {
            navegarAPrincipal(UsuarisFragment())
        }
        view.findViewById<Button>(R.id.btnRegistre).setOnClickListener {
            navegarA(RegistreFragment())
        }
    }
}
