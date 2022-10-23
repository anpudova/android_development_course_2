package com.example.task4

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task4.databinding.ItemCategoryBinding
import com.example.task4.databinding.ItemFavoriteBinding
import com.example.task4.databinding.ItemPlaylistBinding

class SongsViewTypeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataList: ArrayList<ItemModel> = arrayListOf()
    var onItemFavoriteClickListener: ((ItemModel) -> Unit)? = null
    var onItemPlaylistClickListener: ((ItemModel) -> Unit)? = null
    var positionFavorite: Int = 2
    var positionCategory: Int = 10
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            RecyclerViewTypes.FAVORITE_TYPE.typeValue -> {
                FavoriteViewHolder(
                    binding = ItemFavoriteBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
            RecyclerViewTypes.CATEGORIES_TYPE.typeValue -> {
                CategoriesViewHolder(
                    binding = ItemCategoryBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
            else -> {
                PlaylistViewHolder(
                    binding = ItemPlaylistBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < positionFavorite) {
            RecyclerViewTypes.FAVORITE_TYPE.typeValue
        } else if (position < positionCategory) {
            RecyclerViewTypes.CATEGORIES_TYPE.typeValue
        } else {
            RecyclerViewTypes.PLAYLIST_TYPE.typeValue
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            RecyclerViewTypes.FAVORITE_TYPE.typeValue -> {
                (holder as? FavoriteViewHolder)?.bindFavoriteItem(dataList[position])
            }
            RecyclerViewTypes.CATEGORIES_TYPE.typeValue -> {
                (holder as? CategoriesViewHolder)?.bindCategoryItem(dataList[position])
            }
            RecyclerViewTypes.PLAYLIST_TYPE.typeValue -> {
                (holder as? PlaylistViewHolder)?.bindPlaylistItem(dataList[position])
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    inner class FavoriteViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                ivFavorite.setOnClickListener {
                    onItemFavoriteClickListener?.invoke(dataList[adapterPosition])
                    notifyItemChanged(adapterPosition)
                }
            }
        }
        fun bindFavoriteItem(item: ItemModel) {
            with(binding) {
                ivFavorite.setImageResource(item.image)
                tvFavorite.text = item.name_text
                tvTracks.text = item.tracks_text
            }
        }
    }

    inner class CategoriesViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindCategoryItem(item: ItemModel) {
            with(binding) {
                ivCategory.setImageResource(item.image)
                tvCategory.text = item.name_text
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class PlaylistViewHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                rlPlaylist.setOnClickListener {
                    onItemPlaylistClickListener?.invoke(dataList[adapterPosition])
                    notifyDataSetChanged()
                }
            }
        }
        fun bindPlaylistItem(item: ItemModel) {
            with(binding) {
                ivPlaylist.setImageResource(item.image)
                tvPlaylist.text = item.name_text
                tvPlaylistTracks.text = item.tracks_text
                if (adapterPosition == positionCategory) {
                    tvHead.visibility = View.VISIBLE
                } else {
                    tvHead.visibility = View.GONE
                }
            }
        }
    }
}