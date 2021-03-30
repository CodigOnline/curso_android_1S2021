package com.codigonline.firebase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codigonline.firebase.databinding.ActivityProductoBinding

class ProductoActivitiy : AppCompatActivity() {
    private val TAG = "MAIN_ACTIVITY"
    private lateinit var binding: ActivityProductoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}