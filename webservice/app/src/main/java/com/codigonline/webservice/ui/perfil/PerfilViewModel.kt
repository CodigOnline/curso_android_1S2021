package com.codigonline.webservice.ui.perfil

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codigonline.webservice.application.App
import com.codigonline.webservice.services.RetrofitService
import com.codigonline.webservice.services.entities.Usuario
import com.codigonline.webservice.services.repositories.UsuarioService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PerfilViewModel : ViewModel() {
    val service = RetrofitService.getRetrofit().create(UsuarioService::class.java)


    fun actualizar(usuario: Usuario.Update) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val id = App.getUsuario().id
                val token = App.getToken()
                service.updateById(token!!, id, usuario)
                    .enqueue(object : Callback<Usuario.Get> {
                        override fun onResponse(
                            call: Call<Usuario.Get>,
                            response: Response<Usuario.Get>
                        ) {
                            Log.d("TAG_PERFIL", response.body().toString())
                            if (response.isSuccessful) {

                                Log.d("TAG_PERFIL", "ACTUALIZADO")
                            } else {
                                Log.d("TAG_PERFIL", "ERROR AL ACTUALIZAR")
                            }
                        }

                        override fun onFailure(call: Call<Usuario.Get>, t: Throwable) {
                            t.printStackTrace()
                        }

                    })
            }
        }
    }
}