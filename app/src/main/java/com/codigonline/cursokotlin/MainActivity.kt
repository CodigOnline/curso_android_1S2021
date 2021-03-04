package com.codigonline.cursokotlin


import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.codigonline.cursokotlin.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Base_MyTheme)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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
        }

        binding.mainBtnRegistro.setOnClickListener {
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }


    }

}