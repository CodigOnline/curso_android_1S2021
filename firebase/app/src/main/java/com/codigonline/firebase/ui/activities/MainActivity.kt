package com.codigonline.firebase.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.codigonline.firebase.App
import com.codigonline.firebase.R
import com.codigonline.firebase.databinding.ActivityMainBinding
import com.codigonline.firebase.databinding.NavHeaderMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val TAG = "MAIN_ACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        drawerLayout.addDrawerListener(object :
            ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)

                val navView: NavigationView = findViewById(R.id.nav_view)
                val headerView = navView.getHeaderView(0)
                val headerViewBining = NavHeaderMainBinding.bind(headerView)

                val user = App.getAuth().currentUser
                headerViewBining.headerUsername.text = user.displayName
                headerViewBining.headerMail.text = user.email

                if (user.photoUrl != null) {

                    Log.d(TAG, user.photoUrl.toString())

                    val circularProgress = CircularProgressDrawable(this@MainActivity)
                    circularProgress.strokeWidth = 5f
                    circularProgress.centerRadius = 30f
                    circularProgress.start()

                    Glide
                        .with(this@MainActivity)
                        .load(user.photoUrl)
                        .fitCenter()
                        .placeholder(circularProgress)
                        .into(headerViewBining.headerImg)


                }

            }
        })
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                val navController = findNavController(R.id.nav_host_fragment)
                navController.navigate(R.id.profileFragment)
                return true
            }
            R.id.action_close -> {
                App.getAuth().signOut()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
        }
        return false
    }
}