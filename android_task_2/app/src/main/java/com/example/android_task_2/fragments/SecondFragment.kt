package com.example.android_task_2.fragments

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.android_task_2.R
import com.example.android_task_2.databinding.FragmentSecondBinding

class SecondFragment: Fragment(R.layout.fragment_second) {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSecondBinding.bind(view)

        with (binding) {
            (ll1.layoutParams as? LinearLayout.LayoutParams)?.apply {
                width = LinearLayout.LayoutParams.MATCH_PARENT
                height = LinearLayout.LayoutParams.WRAP_CONTENT
            }
            ll1.orientation = LinearLayout.HORIZONTAL

            (v1.layoutParams as? LinearLayout.LayoutParams)?.apply {
                width = 0
                height = LinearLayout.LayoutParams.WRAP_CONTENT
                weight = 1F
            }
            (v2.layoutParams as? LinearLayout.LayoutParams)?.apply {
                width = 0
                height = LinearLayout.LayoutParams.WRAP_CONTENT
                weight = 1F
            }

            (ll2.layoutParams as? LinearLayout.LayoutParams)?.apply {
                width = LinearLayout.LayoutParams.MATCH_PARENT
                height = LinearLayout.LayoutParams.WRAP_CONTENT
            }
            ll2.orientation = LinearLayout.HORIZONTAL
            (v3.layoutParams as? LinearLayout.LayoutParams)?.apply {
                width = 0
                height = LinearLayout.LayoutParams.WRAP_CONTENT
                weight = 1F
            }
            (v4.layoutParams as? LinearLayout.LayoutParams)?.apply {
                width = 0
                height = LinearLayout.LayoutParams.WRAP_CONTENT
                weight = 1F
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