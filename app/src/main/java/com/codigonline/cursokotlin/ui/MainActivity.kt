package com.codigonline.cursokotlin.ui


import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.codigonline.cursokotlin.R
import com.codigonline.cursokotlin.databinding.ActivityMainBinding
import com.codigonline.cursokotlin.ui.A.AActivity
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val CODE_RESULT_REGISTRO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Base_MyTheme)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root;
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
            val intent = Intent(this, AActivity::class.java)
            startActivity(intent)

        }

        binding.mainBtnRegistro.setOnClickListener {

            val intent = Intent(this, RegistroActivity::class.java)
            //val intent = Intent(this, Activity2::class.java)
            startActivityForResult(
                intent,
                CODE_RESULT_REGISTRO,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            )
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CODE_RESULT_REGISTRO -> {
                if (resultCode == AppCompatActivity.RESULT_OK) {
                    val extras = data!!.extras!!
                    val email = extras.get("email")
                    val password = extras.get("password")
                    binding.mainTieEmail.setText(email.toString())
                    binding.mainTiePassword.setText(password.toString())
                }
            }
        }
    }

}