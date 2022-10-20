package com.example.work_3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.work_3.databinding.ItemColorBinding

class ColorsAdapter: RecyclerView.Adapter<ColorsAdapter.ItemColorViewHolder>() {

    var onItemClickListener: ((ItemModel) -> Unit)? = null

    var items: MutableList<ItemModel> = mutableListOf()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemColorViewHolder {
        return ItemColorViewHolder(
            _binding = ItemColorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemColorViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ItemColorViewHolder(
        private var _binding: ItemColorBinding
    ) : RecyclerView.ViewHolder(_binding.root) {

        init {
            with (_binding) {
                root.setOnClickListener {
                    onItemClickListener?.invoke(items[adapterPosition])
                    notifyItemChanged(adapterPosition)
                }
                btnItem.setOnClickListener {
                    onItemClickListener?.invoke(items[adapterPosition])
                }
            }
        }

        fun bindItem(item: ItemModel) {
            with(_binding) {
                btnItem.text = item.buttonText
                btnItem.setBackgroundColor(
                    ContextCompat.getColor(
                        root.context,
                        item.color
                    )
                )
            }

        }
    }
}