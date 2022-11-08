package com.example.task_5.fragments

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.task_5.R
import com.example.task_5.databinding.FragmentFirstBinding

class FirstFragment: Fragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)

        with(binding) {
            btnOnw.setOnClickListener {
                findNavController().navigate(
                    R.id.action_firstFragment_to_firstTwoFragment
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
        println("TEST TAG - FirstFragment onDestroy")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - FirstFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - FirstFragment onPause")
    }
}