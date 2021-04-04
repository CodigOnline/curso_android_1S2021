package com.codigonline.firebase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.codigonline.firebase.App
import com.codigonline.firebase.R
import com.codigonline.firebase.databinding.ActivityRegistroBinding
import com.codigonline.firebase.entities.Usuario
import com.codigonline.firebase.utils.Constantes
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*


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
                        auth.currentUser.apply {
                            val profile = UserProfileChangeRequest.Builder()
                                .setDisplayName(
                                    binding.registroTieName.getString()
                                )
                                .build()
                            updateProfile(profile).addOnCompleteListener { taskChangeProfile ->
                                if (taskChangeProfile.isSuccessful) {
                                    //AMPLAIR LOS DATOS DEL USER
                                    App.getFirestore()
                                        .collection(Constantes.USUARIOS)
                                        .document(auth.currentUser.uid)
                                        .set(
                                            Usuario(
                                                binding.registroTieName.getString(),
                                                binding.registroTieMail.getString(),
                                                18,
                                                Date(System.currentTimeMillis()),
                                                Date(System.currentTimeMillis())
                                            )
                                        )
                                        .addOnCompleteListener { taskNewUser ->
                                            if (taskNewUser.isSuccessful) {
                                                finish()
                                            } else {
                                                Snackbar.make(
                                                    view,
                                                    getString(R.string.error_add_user_collection),
                                                    Snackbar.LENGTH_LONG
                                                )
                                                    .show()
                                            }
                                        }


                                } else {
                                    Snackbar.make(
                                        view,
                                        getString(R.string.error_change_username),
                                        Snackbar.LENGTH_LONG
                                    )
                                        .show()
                                }


                            }

                        }
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

