package com.example.work_3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.work_3.databinding.FragmentSecondBinding

class SecondFragment: Fragment(R.layout.fragment_second) {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSecondBinding.bind(view)

        val headText = arguments?.getString("argHead")
        val image =  arguments?.getInt("argImage")
        val contentText =  arguments?.getString("argContent")

        with(binding) {
            ivColor.setImageResource(image!!.toInt())
            tvHead.text = headText.toString()
            tvContent.text = contentText.toString()
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