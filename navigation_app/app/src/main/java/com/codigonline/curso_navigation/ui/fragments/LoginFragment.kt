package com.codigonline.curso_navigation.ui.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.NavHostFragment
import com.codigonline.curso_navigation.R
import com.codigonline.curso_navigation.databinding.FragmentLoginBinding
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

            val editPassword = binding.mainTiePassword
            val password = editPassword.text

            if (password.isNullOrBlank()) {
                binding.mainTilPassword.error = getString(R.string.error_blank_password)
                return@setOnClickListener
            }

            val sharedPrefs = activity?.getPreferences(Context.MODE_PRIVATE)!!
            with(sharedPrefs.edit()){
                putBoolean("logueado",true)
                apply()
            }

           /* val edit = sharedPrefs.edit()
            edit.putBoolean("logueado",true)
            edit.apply()*/


            NavHostFragment.findNavController(this).navigate(R.id.action_to_bottom_nav_graph)
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