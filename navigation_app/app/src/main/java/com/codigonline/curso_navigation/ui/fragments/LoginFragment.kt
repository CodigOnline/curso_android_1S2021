package com.codigonline.curso_navigation.ui.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.codigonline.curso_navigation.R
import com.codigonline.curso_navigation.databinding.FragmentLoginBinding
import com.codigonline.curso_navigation.viewModels.UsuarioViewModel
import com.google.android.material.textfield.TextInputEditText


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)


        val view = _binding!!.root
        val binding = _binding!!


        val data = arguments?.let {
            LoginFragmentArgs.fromBundle(it).data
        }

        data.let {
            binding.mainTieEmail.setText(it?.get(0))
            binding.mainTiePassword.setText(it?.get(1))
        }

        binding.mainTiePassword.setOnFocusChangeListener { _, hasFocus ->
            run {
                if (hasFocus) {
                    binding.mainTilPassword.error = ""
                }
            }
        }


        binding.mainTiePassword.addTextChangedListener {
            val size = it!!.length
            if (size < 5) {
                binding.mainTilPassword.error = "Caracteres $size/5"
            } else {
                binding.mainTilPassword.error = ""
            }
        }
        binding.mainTiePassword.setOnClickListener {
            val textInputEditText = it as TextInputEditText //Casteo

            //Recuperamos el texto del editext lo convertimos a string y de este String comprobamos su longitud
            val size = textInputEditText.text.toString().length
            if (size < 5) {
                binding.mainTilPassword.error = "Caracteres $size/5"
            } else {
                binding.mainTilPassword.error = ""
            }
        }

        binding.mainBtnLogin.setOnClickListener {

            val mail = binding.mainTieEmail
            val editPassword = binding.mainTiePassword
            val password = editPassword.text

            if (password.isNullOrBlank()) {
                binding.mainTilPassword.error = getString(R.string.error_blank_password)
                return@setOnClickListener
            }
            if (mail.text.isNullOrBlank()) {
                return@setOnClickListener
            }


            /* val edit = sharedPrefs.edit()
             edit.putBoolean("logueado",true)
             edit.apply()*/

            //RECUPERAR EL USUARIO DE LA DB
            //SI EL USUARIO EXISTE COMPROBAR SI LA CONTRASEÑA COINCIDE
            //SI ES TO_DO CORRECTO UTILIZAMOS EL NAVHOSTFRAGMENT PARA IR AL SIGUIENTE FRAGMENT

            val usuarioViewModel: UsuarioViewModel by viewModels()
            usuarioViewModel.login(mail.text.toString()).observe(viewLifecycleOwner, {

                if (it != null) {
                    if (it.password == password.toString()) { //COMPROBAMOS SI LA CONTRASEÑA ES IGUAL
                        val sharedPrefs = activity?.getPreferences(Context.MODE_PRIVATE)!!
                        with(sharedPrefs.edit()) {
                            putBoolean("logueado", true)
                            putString("nombreUsuario", it.nombre)
                            putLong("idUsuario", it.id)
                            apply()
                        }
                        NavHostFragment.findNavController(this).navigate(R.id.action_to_bottom_nav_graph)
                    } else {
                        Toast.makeText(requireContext(), "El usuario/contraseña introducido es incorrecto", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "El usuario/contraseña introducido es incorrecto", Toast.LENGTH_SHORT).show()
                }

            })


        }

        binding.mainBtnRegistro.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_to_registroFragment)
        }
        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}