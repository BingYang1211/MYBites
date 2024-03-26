package com.example.mybites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.mybites.databinding.HomeBinding
import com.example.mybites.fragment.AboutUsFragment
import com.example.mybites.fragment.HomeFragment
import com.example.mybites.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: HomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate executed")
        binding = HomeBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        replaceFragment(HomeFragment())



        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.aboutUs -> replaceFragment(AboutUsFragment())
                R.id.home -> replaceFragment(HomeFragment())
                R.id.account -> replaceFragment(ProfileFragment())
                else -> {

                }
            }
            true
        }

        binding.bottomNavigationView.menu.findItem(R.id.home)?.isChecked = true


    }

    private fun replaceFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
}