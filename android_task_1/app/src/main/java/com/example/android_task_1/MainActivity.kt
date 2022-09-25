package com.example.android_task_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_task_1.databinding.ActivityMainBinding
import com.example.android_task_1.fragments.FirstFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val fragmentsContainerId: Int = R.id.fragments_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(fragmentsContainerId, FirstFragment.getInstance(), FirstFragment.FIRST_FRAGMENT_TAG)
            .commit()
    }
}