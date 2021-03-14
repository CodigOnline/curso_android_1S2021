package com.codigonline.curso_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.NavHostFragment
import com.codigonline.curso_navigation.databinding.FragmentRegistroBinding
import com.google.android.material.textfield.TextInputEditText


class RegistroFragment : Fragment() {


    private var _binding: FragmentRegistroBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        val binding = _binding!!
        val view = binding.root

        binding.registroTieName.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) binding.registroNombre.error = ""
        }
        binding.registroTieMail.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) binding.registroEmail.error = ""
        }
        binding.registroTiePass1.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) binding.registroPassword.error = ""
        }
        binding.registroTiePass2.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) binding.registroRepetirPassword.error = ""
        }
        binding.registroTieName.setOnClickListener {
            val text = it as TextInputEditText
            if (text.text!!.isNotEmpty()) {

                binding.registroNombre.error = ""
            }
        }
        binding.registroTieMail.setOnClickListener {
            val text = it as TextInputEditText
            if (text.text!!.isNotEmpty()) {
                binding.registroEmail.error = ""
            }
        }
        binding.registroTiePass1.setOnClickListener {
            val text = it as TextInputEditText
            if (text.text!!.isNotEmpty()) {
                binding.registroPassword.error = ""

            }
        }
        binding.registroTiePass2.setOnClickListener {
            val text = it as TextInputEditText
            if (text.text!!.isNotEmpty()) {

                binding.registroRepetirPassword.error = ""

            }
        }

        binding.registroTiePass2.setOnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.registroBtnRegistrar.performClick()
            }
            false

        }

        binding.registroBtnRegistrar.setOnClickListener {

            val nombre = binding.registroTieName
            val email = binding.registroTieMail
            val pass1 = binding.registroTiePass1
            val pass2 = binding.registroTiePass2

            val attr = listOf(nombre, email, pass1, pass2)
            var error = false;
            attr.forEach {
                if (it.obtenerTexto().isBlank()) {
                    error = true
                    when (it.id) {
                        R.id.registro_tie_name -> {
                            binding.registroNombre.error = "Debes de introducir tu nombre completo"
                        }
                        R.id.registro_tie_mail -> {
                            binding.registroEmail.error = "Debes de introducir tu email"
                        }
                        R.id.registro_tie_pass1 -> {
                            binding.registroPassword.error = "Debes de introducir una contraseña"
                        }
                        R.id.registro_tie_pass2 -> {
                            binding.registroRepetirPassword.error =
                                "Debes de introducir nuevamente la contraseña"

                        }
                    }
                }
            }
            if (error)
                return@setOnClickListener
            if (pass1.obtenerTexto().length < 5) {
                binding.registroPassword.error = "La contraseña debe de tener como mínimo 5 letras"
                return@setOnClickListener
            }
            if (!pass1.obtenerTexto().equals(pass2.obtenerTexto())) {
                binding.registroPassword.error = "Las contraseñas no coinciden"
                binding.registroRepetirPassword.error = "Las contraseñas no coinciden"
                return@setOnClickListener
            }

            val actions = RegistroFragmentDirections.actionToLoginFragment(setData())

            NavHostFragment.findNavController(this).navigate(actions)




        }

        binding.registroBtnCancelar.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_to_loginFragment)
        }

        return view
    }

    /*
    fun setData():Array<String> {
        val data = arrayOf(_binding!!.registroTieMail.text.toString(),_binding!!.registroTiePass1.text.toString())
        return data
    }*/

    fun setData() = arrayOf(
        _binding!!.registroTieMail.text.toString(),
        _binding!!.registroTiePass1.text.toString()
    )


    fun TextInputEditText.obtenerTexto(): String {
        return text.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}