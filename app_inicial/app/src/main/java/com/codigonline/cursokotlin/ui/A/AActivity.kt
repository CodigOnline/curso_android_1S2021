package com.codigonline.cursokotlin.ui.A

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codigonline.cursokotlin.R
import com.codigonline.cursokotlin.databinding.ActivityABinding

class AActivity : AppCompatActivity() {

    lateinit var binding: ActivityABinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityABinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}