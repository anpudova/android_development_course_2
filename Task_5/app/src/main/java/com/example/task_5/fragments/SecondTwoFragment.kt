package com.example.task_5.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.task_5.R
import com.example.task_5.databinding.FragmentSecondTwoBinding

class SecondTwoFragment: Fragment(R.layout.fragment_second_two) {
    private var _binding: FragmentSecondTwoBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSecondTwoBinding.bind(view)

        with(binding) {
            val text = arguments?.getString("arg-text").orEmpty()
            tvSecond.text = text
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
        println("TEST TAG - SecondTwoFragment onDestroy")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - SecondTwoFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - SecondTwoFragment onPause")
    }
}