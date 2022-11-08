package com.example.task_5.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task_5.R
import com.example.task_5.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar

class FirstTreeFragment: Fragment(R.layout.fragment_first_tree) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Snackbar.make(view, "Hello", Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("TEST TAG - FirstTreeFragment onDestroy")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - FirstTreeFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - FirstTreeFragment onPause")
    }
}