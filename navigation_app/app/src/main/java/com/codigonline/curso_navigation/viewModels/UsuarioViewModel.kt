package com.codigonline.curso_navigation.viewModels

import androidx.lifecycle.*
import com.codigonline.curso_navigation.application.App
import com.codigonline.curso_navigation.database.entities.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuarioViewModel : ViewModel() {
    val db = App.obtenerDB()


    fun save(usuario: Usuario) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.usuarioDao().save(usuario)
            }
        }
    }

    fun login(email: String): LiveData<Usuario> {
        val liveData = MutableLiveData<Usuario>()
        viewModelScope.launch {
            val usuario = withContext(Dispatchers.IO) {
                db.usuarioDao().findOneByEmail(email)
            }
            withContext(Dispatchers.IO) {
                db.usuarioDao().update(usuario)
            }
            liveData.postValue(usuario)
        }
        return liveData
    }
}