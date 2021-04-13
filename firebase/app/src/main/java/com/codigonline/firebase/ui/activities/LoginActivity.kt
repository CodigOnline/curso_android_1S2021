package com.codigonline.firebase.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.codigonline.firebase.App
import com.codigonline.firebase.R
import com.codigonline.firebase.databinding.ActivityLoginBinding
import com.codigonline.firebase.viewModel.UsuarioViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuthInvalidUserException


class LoginActivity : AppCompatActivity() {
    private val TAG = "MAIN_ACTIVITY"
    private lateinit var binding: ActivityLoginBinding

    private val model: UsuarioViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.mainBtnRegistro.setOnClickListener {

            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)

        }

        binding.mainBtnLogin.setOnClickListener {
            val email = binding.mainTieEmail
            val password = binding.mainTiePassword
            if (email.getString().isNullOrBlank()) {
                Snackbar.make(view, R.string.field_null, Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (password.getString().isNullOrBlank()) {
                Snackbar.make(view, R.string.field_null, Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            binding.progressLayout.myProgressBar.visibility = View.VISIBLE

            model.login(email.getString(), password.getString()).observe(this, { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    goToMain()

                } else {
                    binding.progressLayout.myProgressBar.visibility = View.GONE
                    Log.d(TAG, task.exception.toString())
                    when (task.exception) {
                        is FirebaseAuthInvalidUserException -> {

                            Snackbar.make(
                                    view,
                                    "Debes registrarte para acceder",
                                    Snackbar.LENGTH_LONG
                            ).show()
                        }
                        else -> {

                            Snackbar.make(view, "Error al iniciar sesión", Snackbar.LENGTH_LONG)
                                    .show()
                        }
                    }
                }
            })

        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = App.getAuth().currentUser
        if (currentUser != null) {
            Log.d(TAG, currentUser.email!!)
            Log.d(TAG, currentUser.displayName!!)
            Log.d(TAG, currentUser.uid)
            goToMain()
        }
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun TextInputEditText.getString(): String {
        return text.toString()
    }

}