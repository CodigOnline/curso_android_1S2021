package com.codigonline.firebase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.codigonline.firebase.App
import com.codigonline.firebase.R
import com.codigonline.firebase.databinding.ActivityRegistroBinding
import com.codigonline.firebase.entities.Usuario
import com.codigonline.firebase.utils.Constantes
import com.codigonline.firebase.viewModel.UsuarioViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.ktx.Firebase
import java.util.*


class RegistroActivity : AppCompatActivity() {
    private val TAG = "REGISTRO_ACTIVITY"
    private lateinit var binding: ActivityRegistroBinding

    private val model: UsuarioViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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
            val usuario = Usuario(
                    binding.registroTieName.getString(),
                    binding.registroTieMail.getString(),
                    18,
                    Date(System.currentTimeMillis()),
                    Date(System.currentTimeMillis())
            )

            binding.registroProgressBar.myProgressBar.visibility= View.VISIBLE
            model.registro(usuario, password1.getString()).observe(this, { exception ->
                if (exception == null) {
                    finish()
                } else {
                    binding.registroProgressBar.myProgressBar.visibility= View.GONE
                    Log.d(TAG, exception.toString())
                    when (exception) {
                        is FirebaseAuthUserCollisionException -> {
                            Snackbar.make(
                                    view,
                                    getString(R.string.email_in_use),
                                    Snackbar.LENGTH_LONG
                            )
                                    .show()
                        }
                        is FirebaseFirestoreException -> {
                            Snackbar.make(
                                    view,
                                    getString(R.string.error_add_user_collection),
                                    Snackbar.LENGTH_LONG
                            )
                                    .show()
                        }
                        is FirebaseAuthInvalidUserException -> {
                            Snackbar.make(
                                    view,
                                    getString(R.string.error_change_username),
                                    Snackbar.LENGTH_LONG
                            )
                                    .show()
                        }
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
            })

        }
    }

    fun TextInputEditText.getString(): String {
        return text.toString()
    }
}
