package com.example.android_task_1.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.android_task_1.R
import com.example.android_task_1.databinding.FragmentSecondBinding

class SecondFragment: Fragment(R.layout.fragment_second) {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSecondBinding.bind(view)

        val text1 = arguments?.getString("argCount")?.toInt()
        val text2 =  arguments?.getString("argColor")?.toInt()

        if (text1 != 0) {
            binding.tvCounter.text = text1.toString()
            binding.tvCounter.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.lilac_100))
        }

        if (text2 != 0) {
            val recolor: Int = text2!!
            if (recolor % 3 == 0) {
                binding.ivColor.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
            } else if (recolor % 3 == 1) {
                binding.ivColor.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.orange))
            } else {
                binding.ivColor.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.yellow))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("TEST TAG - SecondFragment onDestroyView")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - SecondFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - SecondFragment onPause")
    }

    companion object {
        const val SECOND_FRAGMENT_TAG = "SECOND_FRAGMENT_TAG"
        fun getInstance() = SecondFragment()
    }
}