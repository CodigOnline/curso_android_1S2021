package com.codigonline.firebase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codigonline.firebase.databinding.ActivityRegistroBinding
import com.google.android.material.textfield.TextInputEditText


class RegistroActivity : AppCompatActivity() {
    private val TAG = "REGISTRO_ACTIVITY"
    private lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registroBtnRegistrar.setOnClickListener {
            val nombre = binding.registroTieName
            val email = binding.registroTieMail
            val password1 = binding.registroTiePass1
            val password2 = binding.registroTiePass2

        }
    }

    fun TextInputEditText.getString(): String {
        return text.toString()
    }
}