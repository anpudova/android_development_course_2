package com.example.task_5.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task_5.R
import com.example.task_5.databinding.FragmentThirdBinding

class ThirdFragment: Fragment(R.layout.fragment_third) {
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentThirdBinding.bind(view)

        with(binding) {
            btnOnw.setOnClickListener {
                findNavController().navigate(
                    R.id.action_thirdFragment_to_thirdTwoFragment
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
        println("TEST TAG - ThirdFragment onDestroy")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - ThirdFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - ThirdFragment onPause")
    }
}