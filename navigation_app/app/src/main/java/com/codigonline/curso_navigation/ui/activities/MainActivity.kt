package com.codigonline.curso_navigation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.codigonline.curso_navigation.listeners.MainListener
import com.codigonline.curso_navigation.R
import com.codigonline.curso_navigation.application.App
import com.codigonline.curso_navigation.database.AppDatabase
import com.codigonline.curso_navigation.database.entities.Usuario
import com.codigonline.curso_navigation.databinding.ActivityMainBinding
import com.codigonline.curso_navigation.listeners.BottomNavListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), MainListener, BottomNavListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                App.obtenerDB().mainDao().init()
            }
        }


        val bottomNavView = binding.bottomNavView
        val navController = findNavController(R.id.nav_host_fragment)
        bottomNavView.setupWithNavController(navController)


    }

    override fun showBottomNavigation() {
        binding.bottomNavView.visibility = View.VISIBLE
    }

    override fun updateBadge(cantidad: Int) {
        val badge = binding.bottomNavView.getOrCreateBadge(R.id.opinionesFragment)
        badge.number = cantidad
        badge.isVisible = cantidad > 0
    }


}