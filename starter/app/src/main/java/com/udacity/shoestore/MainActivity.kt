package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.screens.listing.ListingViewModel
import timber.log.Timber
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var listViewModel: ListingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController


        listViewModel = ViewModelProvider(this).get(ListingViewModel::class.java)

        navController.let {
            val appBarConfiguration = AppBarConfiguration
                    .Builder()
                    .setFallbackOnNavigateUpListener {
                        onBackPressed()
                        true
                    }.build()
            navController.setGraph(R.navigation.navigation)
            setSupportActionBar(binding.toolbar)
            binding.toolbar.setupWithNavController(it, appBarConfiguration)
        }
    }
}
