package com.example.task_7.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task_7.models.ItemModel
import com.example.task_7.databinding.ItemCityBinding

class CitiesAdapter: RecyclerView.Adapter<CitiesAdapter.ItemViewHolder>() {

    var items: List<ItemModel> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            _binding = ItemCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ItemViewHolder(
        private var _binding: ItemCityBinding
    ) : RecyclerView.ViewHolder(_binding.root) {

        fun bindItem(item: ItemModel) {
            with(_binding) {
                tvNumber.text = item.number.toString()
                tvNameCity.text = item.name_city
            }
        }
    }
}