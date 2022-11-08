package com.example.task_5.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.task_5.R
import com.example.task_5.databinding.FragmentFirstTwoBinding

class FirstTwoFragment: Fragment(R.layout.fragment_first_two) {

    private var _binding: FragmentFirstTwoBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstTwoBinding.bind(view)

        with(binding) {
            btnOnw.setOnClickListener {
                findNavController().navigate(
                    R.id.action_firstTwoFragment_to_firstTreeFragment
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
        println("TEST TAG - FirstTwoFragment onDestroy")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - FirstTwoFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - FirstTwoFragment onPause")
    }
}