package com.example.task_7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task_7.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment: BottomSheetDialogFragment(R.layout.fragment_bottom_sheet) {

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBottomSheetBinding.bind(view)
        initDialog()
    }

    private fun initDialog() {
        with(binding) {
            tvNumber.setOnClickListener {
                parentFragmentManager.setFragmentResult(REQ_KEY, bundleOf(DATA_KEY to "sort-num"))
                findNavController().navigate(
                    R.id.action_bottomSheetFragment_to_firstFragment
                )
            }
            tvNameCity.setOnClickListener {
                parentFragmentManager.setFragmentResult(REQ_KEY, bundleOf(DATA_KEY to "sort-alph"))
                findNavController().navigate(
                    R.id.action_bottomSheetFragment_to_firstFragment
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
        println("TEST TAG - BottomSheetFragment onDestroy")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - BottomSheetFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - BottomSheetFragment onPause")
    }

    companion object {
        const val REQ_KEY = "reqKey"
        const val DATA_KEY = "dataKey"
    }
}