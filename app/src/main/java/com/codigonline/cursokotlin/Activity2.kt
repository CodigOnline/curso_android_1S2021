package com.codigonline.cursokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class Activity2 : AppCompatActivity() {
    private val TAG = "ACTIVITY2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        //Recibir el contenido enviado desde el MainActivity
        val extra = intent.extras!!
        val user = extra.get("idusuario")
        Log.d(TAG, user.toString())
    }
}