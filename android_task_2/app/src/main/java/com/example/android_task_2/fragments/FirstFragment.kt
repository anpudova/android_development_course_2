package com.example.android_task_2.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.android_task_2.R
import com.example.android_task_2.databinding.FragmentFirstBinding


class FirstFragment : Fragment(R.layout.fragment_first){
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)

        with(binding) {
            etNumber.addTextChangedListener(object : TextWatcher {
                var lastChar: String = ""

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    val digits: Int = etNumber.text.toString().length
                    if (digits > 0) lastChar = etNumber.text.toString().substring(digits - 1)
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    val digits: Int = etNumber.text.length
                    if (digits > 0 && digits != 2 && digits != 6 && digits != 10 && digits != 13) {
                        val last = s.subSequence(digits - 1, digits)
                        if (last.toString() == " ") etNumber.text.delete(digits - 1, digits)
                    }
                    if (digits == 1 || digits == 5 || digits == 9 || digits == 12) {
                        if (lastChar != " ") etNumber.append(" ")
                        else etNumber.text.delete(digits - 1, digits)
                    }
                    val pattern = "8\\s9\\d{2}\\s\\d{3}\\s\\d{2}\\s\\d{2}".toRegex()
                    etName.isEnabled = pattern.matches(etNumber.text)
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            etName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun afterTextChanged(s: Editable?) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    val pattern = "(\\s*[а-яА-Яa-zA-z0-9]\\s*){5,}".toRegex()
                    if (pattern.matches(etName.text)) {
                        btnStart.isEnabled = true
                        btnStart.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.beige_100))
                    } else {
                        btnStart.isEnabled = false
                        btnStart.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_100))
                    }

                    btnStart.setOnClickListener {
                        val fragment = SecondFragment()
                        val fm: FragmentManager = parentFragmentManager
                        fm.beginTransaction().add(R.id.fragments_container, fragment).detach(fragment)
                        fm.beginTransaction().replace(R.id.fragments_container, fragment).addToBackStack(null).commit()

                    }
                }
            })
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