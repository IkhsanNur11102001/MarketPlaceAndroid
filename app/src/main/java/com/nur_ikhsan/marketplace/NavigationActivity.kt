package com.nur_ikhsan.marketplace

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.nur_ikhsan.marketplace.Util.Prefs
import com.nur_ikhsan.marketplace.databinding.ActivityNavigationsBinding
import com.nur_ikhsan.marketplace.ui.login.LoginActivity

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_navigations)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id. navigation_keranjang
            )
        )
       // setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener {

            if (it.itemId == R.id.navigation_notifications){
                if (Prefs.isLogin){
                    Log.d("TAG", "Sudah Login")
                    navController.navigate(it.itemId)
                }else{
                    startActivity(Intent(this, LoginActivity ::class.java))
                    Log.d("TAG", "belum login pindah ke menu login")
                }
            }else {
                navController.navigate(it.itemId)
                Log.d("TAG", "onCreat : yang lain" + it.itemId)
            }
            return@setOnItemSelectedListener true
        }
    }
}