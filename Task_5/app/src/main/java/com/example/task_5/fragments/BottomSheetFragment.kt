package com.example.task_5.fragments

import BottomSheetAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task_5.ItemModel
import com.example.task_5.R
import com.example.task_5.databinding.FragmentBottomSheetBinding
import com.example.task_5.databinding.FragmentFirstTwoBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment: BottomSheetDialogFragment(R.layout.fragment_bottom_sheet) {

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!
    private var rvAdapter: BottomSheetAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBottomSheetBinding.bind(view)
        initAdapter()
    }

    private fun initAdapter() {
        rvAdapter = BottomSheetAdapter().apply {
            items = listOf(
                ItemModel(R.drawable.one, "First"),
                ItemModel(R.drawable.two, "Second"),
                ItemModel(R.drawable.tree, "Third")
            )
            onItemClickListener = { item ->
                when (item.textView) {
                    "First" ->
                        findNavController().navigate(
                            R.id.action_bottomSheetFragment_to_firstTreeFragment
                        )
                    "Second" -> {
                        val bundle = Bundle()
                        bundle.putString(
                            "arg-text", "text"
                        )
                        findNavController().navigate(
                            R.id.action_bottomSheetFragment_to_secondTwoFragment,
                            bundle
                        )
                    }

                    "Third" ->
                        findNavController().navigate(
                            R.id.action_bottomSheetFragment_to_thirdTwoFragment
                        )
                }
            }
        }
        with(binding) {
            rvMain.adapter = rvAdapter
            rvMain.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
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
}