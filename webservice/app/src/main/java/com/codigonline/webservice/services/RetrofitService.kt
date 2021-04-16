package com.codigonline.webservice.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object {
        private var _retrofit: Retrofit? = null
        fun getRetrofit(): Retrofit {
            if (_retrofit == null) {
                _retrofit = Retrofit.Builder()
                        .baseUrl("https://server.codigonline.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

            }
            return _retrofit!!
        }
    }
}