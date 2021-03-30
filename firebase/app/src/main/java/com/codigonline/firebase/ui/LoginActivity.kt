package com.codigonline.firebase.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.codigonline.firebase.R
import com.codigonline.firebase.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private val TAG = "MAIN_ACTIVITY"
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth

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

            auth.signInWithEmailAndPassword(email.getString(), password.getString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        goToProducts()

                    } else {

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
                }

        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            goToProducts()
        }
    }

    private fun goToProducts() {
        val intent = Intent(this, ProductoActivitiy::class.java)
        startActivity(intent)
        finish()
    }

    fun TextInputEditText.getString(): String {
        return text.toString()
    }

}