package com.codigonline.firebase.viewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codigonline.firebase.App
import com.codigonline.firebase.R
import com.codigonline.firebase.entities.Usuario
import com.codigonline.firebase.utils.Constantes
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class UsuarioViewModel : ViewModel() {

    val auth = App.getAuth()
    val firestore = App.getFirestore()
    private val TAG = "USUARIO_VIEW_MODEL"

    fun login(email: String, password: String): LiveData<Task<AuthResult>> {

        val data = MutableLiveData<Task<AuthResult>>()
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    data.value = task

                }

        return data

    }

    fun registro(usuario: Usuario, password: String): LiveData<Exception?> {
        val data = MutableLiveData<Exception?>()
        auth.createUserWithEmailAndPassword(usuario.email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        auth.currentUser.apply {
                            val profile = UserProfileChangeRequest.Builder()
                                    .setDisplayName(usuario.nombre)
                                    .build()
                            updateProfile(profile).addOnCompleteListener { taskChangeProfile ->
                                if (taskChangeProfile.isSuccessful) {
                                    //AMPLAIR LOS DATOS DEL USER
                                    App.getFirestore()
                                            .collection(Constantes.USUARIOS)
                                            .document(auth.currentUser.uid)
                                            .set(usuario)
                                            .addOnCompleteListener { taskNewUser ->
                                                if (taskNewUser.isSuccessful) {
                                                    data.value = null
                                                } else {
                                                    data.value = taskNewUser.exception
                                                }
                                            }
                                } else {
                                    data.value = taskChangeProfile.exception
                                }
                            }

                        }
                    } else {
                        data.value = task.exception
                    }
                }
        return data
    }

    fun findOneById(uid: String): LiveData<Usuario> {
        val data = MutableLiveData<Usuario>()
        firestore
                .collection(Constantes.USUARIOS)
                .document(uid)
                .get()
                .addOnSuccessListener {
                    val usuario = it.toObject<Usuario>()
                    data.value = usuario!!
                }

        return data

    }

    fun updateUsuario(usuario: Usuario): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        val auth = App.getAuth() //Firebase Authentication
        App.getFirestore().collection(Constantes.USUARIOS).document(auth.currentUser.uid)
                .set(usuario)
                .addOnSuccessListener {
                    auth.currentUser.apply {
                        val profile = UserProfileChangeRequest.Builder()
                                .setDisplayName(usuario.nombre)
                                .setPhotoUri(Uri.parse(usuario.imageUrl))
                                .build()
                        updateProfile(profile).addOnSuccessListener {
                            data.value = true
                        }
                                .addOnFailureListener {
                                    data.value = false
                                }
                    }

                }
                .addOnFailureListener {
                    data.value = false
                }
        return data


    }
}