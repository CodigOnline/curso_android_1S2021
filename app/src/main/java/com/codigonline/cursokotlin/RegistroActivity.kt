package com.codigonline.cursokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.codigonline.cursokotlin.databinding.ActivityRegistroBinding
import com.google.android.material.textfield.TextInputEditText

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Inflate the layout for this fragment
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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

        binding.registroTiePass2.setOnEditorActionListener { v, actionId, event ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.registroBtnRegistrar.performClick()
                true
            }
            false

        }

        binding.registroBtnCancelar.setOnClickListener {
            setResult(AppCompatActivity.RESULT_CANCELED)
            finish()
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
            val intent = Intent()
            intent.putExtra("email", email.obtenerTexto())
            intent.putExtra("password", pass1.obtenerTexto())
            setResult(RESULT_OK, intent)
            finish()
        }

    }

    fun TextInputEditText.obtenerTexto(): String {
        return text.toString()
    }
}