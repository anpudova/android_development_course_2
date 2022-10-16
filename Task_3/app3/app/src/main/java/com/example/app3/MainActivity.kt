package com.example.app3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val fragmentsContainerId: Int = R.id.fragments_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(fragmentsContainerId, FirstFragment.getInstance(), FirstFragment.FIRST_FRAGMENT_TAG)
            .commit()
    }
}