package com.codigonline.cursokotlin


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.codigonline.cursokotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.mainTiePassword.setOnFocusChangeListener { v, hasFocus ->
            run {
                if (hasFocus) {
                    binding.mainTilPassword.error = ""
                }
            }

        }
        binding.mainTiePassword.addTextChangedListener {
            binding.mainTilPassword.error = ""

        }

        binding.mainBtnLogin.setOnClickListener {

            val editPassword = binding.mainTiePassword
            val password = editPassword.text

            if (password.isNullOrBlank()) {
                binding.mainTilPassword.error = getString(R.string.error_blank_password)
                return@setOnClickListener
            }
        }

        binding.mainBtnRegistro.setOnClickListener {

        }


    }

}