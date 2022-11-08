package com.example.task_5.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.task_5.R
import com.example.task_5.databinding.FragmentThirdTreeBinding
import com.google.android.material.snackbar.Snackbar

class ThirdTreeFragment: Fragment(R.layout.fragment_third_tree) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Snackbar.make(view, "Hello", Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("TEST TAG - ThirdTreeFragment onDestroy")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - ThirdTreeFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - ThirdTreeFragment onPause")
    }
}