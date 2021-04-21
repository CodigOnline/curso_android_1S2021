package com.codigonline.webservice.ui.opiniones

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codigonline.webservice.services.RetrofitService
import com.codigonline.webservice.services.entities.Opiniones
import com.codigonline.webservice.services.repositories.OpinionesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OpinionesViewModel : ViewModel() {
    private val TAG = "OPINIONES_VIEW_MODEL"
    private val service = RetrofitService.getRetrofit().create(OpinionesService::class.java)

    private val opiniones: MutableLiveData<Opiniones> by lazy {
        MutableLiveData<Opiniones>().also {
            loadOpiniones()
        }
    }

    fun getOpiniones(): LiveData<Opiniones> {
        return opiniones;
    }

    private fun loadOpiniones() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                service.getAll().enqueue(object : Callback<Opiniones> {
                    override fun onResponse(call: Call<Opiniones>, response: Response<Opiniones>) {
                        val body = response.body()
                        Log.d(TAG, body!!.toString());
                        opiniones.postValue(response.body())
                    }

                    override fun onFailure(call: Call<Opiniones>, t: Throwable) {
                        Log.e(TAG, "Error al utilizar Retrofit")
                        Log.e(TAG, t.message!!)
                    }

                })
            }
        }
    }


}