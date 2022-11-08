package com.example.task_5.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task_5.R
import com.example.task_5.databinding.FragmentThirdTwoBinding

class ThirdTwoFragment: Fragment(R.layout.fragment_third_two) {
    private var _binding: FragmentThirdTwoBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentThirdTwoBinding.bind(view)

        with(binding) {
            btnOnw.setOnClickListener {
                findNavController().navigate(
                    R.id.action_thirdTwoFragment_to_thirdTreeFragment
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
        println("TEST TAG - ThirdTwoFragment onDestroy")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - ThirdTwoFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - ThirdTwoFragment onPause")
    }
}