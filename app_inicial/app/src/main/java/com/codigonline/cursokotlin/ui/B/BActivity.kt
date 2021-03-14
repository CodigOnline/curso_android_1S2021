package com.codigonline.cursokotlin.ui.B

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codigonline.cursokotlin.R
import com.codigonline.cursokotlin.databinding.ActivityABinding
import com.codigonline.cursokotlin.databinding.ActivityBBinding

class BActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //IF comprobar si la panatalla está rotada

        val boton = intent.extras!!.getInt("BOTON_PULSADO")

        val fragment = BFragment.newInstance(boton)
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(binding.activityBFrameLayout.id, fragment)
        transaction.commit()


    }
}