package com.codigonline.webservice.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.codigonline.webservice.R
import com.codigonline.webservice.application.App
import com.codigonline.webservice.databinding.FragmentLoginBinding
import com.codigonline.webservice.services.entities.Usuario
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    val loginViewModel: LoginViewModel by viewModels()

    private var binding: FragmentLoginBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val token = App.getToken()
        if (token != null) {
            NavHostFragment.findNavController(this).navigate(R.id.loginFragment_to_opiniones)
        }
        binding!!.btnLogin.setOnClickListener {
            val usuario = Usuario.Login(
                binding!!.etEmail.text.toString(),
                binding!!.etPassword.text.toString()
            )
            loginViewModel.login(usuario).observe(viewLifecycleOwner, {

                if (it) {
                    NavHostFragment.findNavController(this).navigate(R.id.loginFragment_to_opiniones)
                } else {
                    Snackbar.make(binding!!.root, "Usuario NO logueado", Snackbar.LENGTH_SHORT)
                        .show()
                }

            })
        }

        return binding!!.root
    }

}