package com.codigonline.firebase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.codigonline.firebase.R
import com.codigonline.firebase.databinding.ActivityRegistroBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegistroActivity : AppCompatActivity() {
    private val TAG = "REGISTRO_ACTIVITY"
    private lateinit var binding: ActivityRegistroBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        binding.registroBtnRegistrar.setOnClickListener {
            val nombre = binding.registroTieName
            val email = binding.registroTieMail
            val password1 = binding.registroTiePass1
            val password2 = binding.registroTiePass2
            if (nombre.getString().isNullOrBlank()) {
                Snackbar.make(
                    view,
                    getString(R.string.field_null),
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (email.getString().isNullOrBlank()) {
                Snackbar.make(
                    view,
                    getString(R.string.field_null),
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (password1.getString().isNullOrBlank()) {
                Snackbar.make(
                    view,
                    getString(R.string.field_null),
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (password2.getString().isNullOrBlank()) {
                Snackbar.make(
                    view,
                    getString(R.string.field_null),
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            if (password1.getString() != password2.getString()) {
                Snackbar.make(view, R.string.error_equals_password, Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email.getString(), password1.getString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        finish()
                    } else {
                        when (task.exception) {
                            is FirebaseAuthWeakPasswordException -> {
                                Snackbar.make(view, R.string.password_weak, Snackbar.LENGTH_LONG)
                                    .show()
                            }
                            else -> {
                                Snackbar.make(view, R.string.fail_register, Snackbar.LENGTH_LONG)
                                    .show()
                            }
                        }

                    }
                }


        }
    }

    fun TextInputEditText.getString(): String {
        return text.toString()
    }
}