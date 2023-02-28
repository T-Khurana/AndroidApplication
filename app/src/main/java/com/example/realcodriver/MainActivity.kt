package com.example.realcodriver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.realcodriver.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var topAppBar : MaterialToolbar = findViewById(R.id.topAppBar)

        topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {

                R.id.account -> {
                    // Handle favorite icon press
                    true
                }
                else -> false
            }
        }

        topAppBar.title = "Summary"
        replaceFragment(SummaryFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.summary -> {
                    topAppBar.title = "Summary"
                    replaceFragment(SummaryFragment())
                }
                R.id.alerts -> {
                    topAppBar.title = "Alerts"
                    replaceFragment(AlertsFragment())
                }
                R.id.analytics -> {
                    topAppBar.title = "Analytics"
                    replaceFragment(AnalyticsFragment())
                }

                else -> {}
            }
            true
        }
    }

    private fun replaceFragment (fragment: Fragment){
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }


}