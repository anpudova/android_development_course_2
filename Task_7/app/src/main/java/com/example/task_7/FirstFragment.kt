package com.example.task_7

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task_7.databinding.FragmentFirstBinding
import java.util.*

class FirstFragment : Fragment(R.layout.fragment_first){
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private var rvCitiesAdapter: CitiesAdapter? = null
    private var sortNum = false
    private var sortAlph = false
    private var itemsList: List<ItemModel> = emptyList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)

        itemsList = initList()

        with(binding) {
            btnOnward.setOnClickListener {
                findNavController().navigate(
                    R.id.action_firstFragment_to_secondFragment
                )
            }
            btnFilter.setOnClickListener {
                findNavController().navigate(
                    R.id.action_firstFragment_to_bottomSheetFragment
                )
            }
        }

        initAdapter(itemsList)
        parentFragmentManager.setFragmentResultListener(REQ_KEY, viewLifecycleOwner) { _, bundle ->
            val list: List<ItemModel>
            when (bundle.getString(DATA_KEY)) {
                "sort-num" -> {
                    if (sortNum) {
                        list = itemsList.sortedBy { it.number }
                        sortNum = false
                    } else {
                        list = itemsList.sortedByDescending { it.number }
                        sortNum = true
                    }
                    rvCitiesAdapter!!.items = list
                }
                "sort-alph" -> {
                    if (sortAlph) {
                        list = itemsList.sortedBy { it.name_city }
                        sortAlph = false
                    } else {
                        list = itemsList.sortedByDescending { it.name_city }
                        sortAlph = true
                    }
                    rvCitiesAdapter!!.items = list
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initAdapter(list: List<ItemModel>) {
        rvCitiesAdapter = CitiesAdapter().apply {
            items = list
        }
        with(binding) {
            rvCities.adapter = rvCitiesAdapter
            rvCities.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    private fun initList(): List<ItemModel> {
        return listOf(
            ItemModel(1, "Бостон"),
            ItemModel(2, "Токио"),
            ItemModel(3, "Москва"),
            ItemModel(4, "Амстердам"),
            ItemModel(5, "Иннополис"),
            ItemModel(6, "Казань"),
            ItemModel(7, "Сочи"),
            ItemModel(8, "Анапа"),
            ItemModel(9, "Париж"),
            ItemModel(10, "Сидней"),
            ItemModel(11, "Вашингтон"),
            ItemModel(12, "Денвер"),
            ItemModel(13, "Детройт"),
            ItemModel(14, "Берлин"),
            ItemModel(15, "Мюнхен"),
            ItemModel(16, "Самара"),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("TEST TAG - FirstFragment onDestroy")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - FirstFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - FirstFragment onPause")
    }

    companion object {
        const val REQ_KEY = "reqKey"
        const val DATA_KEY = "dataKey"
    }
}