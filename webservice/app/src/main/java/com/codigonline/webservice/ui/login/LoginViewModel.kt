package com.codigonline.webservice.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codigonline.webservice.services.RetrofitService
import com.codigonline.webservice.services.entities.Usuario
import com.codigonline.webservice.services.repositories.LoginService
import com.codigonline.webservice.utils.JwtUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    val service = RetrofitService.getRetrofit().create(LoginService::class.java)
    fun login(usuario: Usuario.Login): LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                service.login(usuario).enqueue(object : Callback<Usuario.Token> {
                    override fun onResponse(
                        call: Call<Usuario.Token>,
                        response: Response<Usuario.Token>
                    ) {
                        Log.d("TAG", response.body().toString())
                        JwtUtils.decode(response.body()!!.token)
                        liveData.postValue(true)
                    }

                    override fun onFailure(call: Call<Usuario.Token>, t: Throwable) {
                        Log.e("ERROR", t.message.toString())
                        liveData.postValue(false)
                    }

                })
            }
        }
        return liveData;
    }
}