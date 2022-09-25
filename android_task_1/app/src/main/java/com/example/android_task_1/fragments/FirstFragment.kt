package com.example.android_task_1.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.android_task_1.R
import com.example.android_task_1.databinding.FragmentFirstBinding

class FirstFragment: Fragment(R.layout.fragment_first) {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private var counter: Int = 0
    private var recolor: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)

        with (binding) {

            btnOne.setOnClickListener {
                val fragment = SecondFragment()
                val bundle = Bundle()
                bundle.putString("argCount", counter.toString())
                bundle.putString("argColor", recolor.toString())
                fragment.arguments = bundle
                val fm: FragmentManager = parentFragmentManager
                fm.beginTransaction().add(R.id.fragments_container, fragment).commit()
                fm.beginTransaction().replace(R.id.fragments_container, fragment).addToBackStack(null).commit()
            }
            btnTwo.setOnClickListener {
                counter++
            }
            btnThree.setOnClickListener {
                recolor++
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        println("TEST TAG - MainFragment onDestroyView")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - MainFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - MainFragment onPause")
    }

    companion object {
        const val FIRST_FRAGMENT_TAG = "FIRST_FRAGMENT_TAG"
        fun getInstance() = FirstFragment()
    }
}
