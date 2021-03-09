package com.codigonline.cursokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codigonline.cursokotlin.databinding.ActivityPlanetaBinding

class PlanetaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlanetaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanetaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentManager = supportFragmentManager //Controlador de fragmentos dinámicos!!!!

        binding.planetaBtnAdd.setOnClickListener {
            val transaction = fragmentManager.beginTransaction();
            val fragment = PlanetaFragment.newInstance("Alvaro", "alvaro.png")
            transaction.add(binding.planetaLl.id, fragment)
            transaction.commit()
        }

        binding.planetaBtnReplace.setOnClickListener {
            val transaction = fragmentManager.beginTransaction()
            val fragment = PruebaFragment.newInstance("fghjk", "jkl")
            transaction.replace(binding.planetaLl.id, fragment)
            transaction.commit()
        }


    }
}