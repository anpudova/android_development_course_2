package com.example.task4

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task4.databinding.FragmentMainBinding

class MainFragment: Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var rvSongsViewTypeAdapter: SongsViewTypeAdapter? = null
    var changeImage1: Int = 1
    var changeImage2: Int = 1
    var changeImage3: Int = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        initSongsViewTypeAdapter()
    }

    private fun initSongsViewTypeAdapter() {
        rvSongsViewTypeAdapter = SongsViewTypeAdapter().apply {
            dataList = arrayListOf(
                ItemModel(R.drawable.favourite_1, "Favorites", "45 tracks"),
                ItemModel(R.drawable.dislike_1, "Disliked", "10 tracks"),
                ItemModel(R.drawable.playlist, "Playlists", ""),
                ItemModel(R.drawable.tracks, "Tracks", ""),
                ItemModel(R.drawable.albums, "Albums", ""),
                ItemModel(R.drawable.artists, "Artists", ""),
                ItemModel(R.drawable.podcasts, "Podcasts", ""),
                ItemModel(R.drawable.download, "Downloaded tracks", ""),
                ItemModel(R.drawable.tracks_on_device, "Tracks on your device", ""),
                ItemModel(R.drawable.baby, "For kids", ""),
                ItemModel(R.drawable.favourite_1, "Favorites", "45 tracks"),
                ItemModel(R.drawable.lana_del_rey, "Lana Del Rey", "126 tracks"),
                ItemModel(R.drawable.linkin_park, "Linkin Park", "87 tracks"),
                ItemModel(R.drawable.cage_the_elephant, "Cage The Elephant", "58 tracks"),
                ItemModel(R.drawable.imagine_dragons, "Imagine Dragons", "75 tracks")
            )
            onItemFavoriteClickListener = { item ->
                if (item.name_text == "Favorites") {
                    if (changeImage1 % 3 == 0) {
                        item.image = R.drawable.favourite_1
                        changeImage1 = 1
                    } else if (changeImage1 % 3 == 1) {
                        item.image = R.drawable.favourite_3
                        changeImage1++
                    } else {
                        item.image = R.drawable.favourite_2
                        changeImage1++
                    }
                } else if (item.name_text == "Disliked"){
                    if (changeImage2 % 3 == 0) {
                        item.image = R.drawable.dislike_1
                        changeImage2 = 1
                    } else if (changeImage2 % 3 == 1) {
                        item.image = R.drawable.dislike_3
                        changeImage2++
                    } else {
                        item.image = R.drawable.dislike_2
                        changeImage2++
                    }
                } else {
                    if (changeImage3 % 3 == 0) {
                        item.image = R.drawable.friends_1
                        changeImage3 = 1
                    } else if (changeImage3 % 3 == 1) {
                        item.image = R.drawable.friends_3
                        changeImage3++
                    } else {
                        item.image = R.drawable.friends_2
                        changeImage3++
                    }
                }
            }
            onItemPlaylistClickListener = {
                val sizeDataList: Int = itemCount
                val position: Int = (0 until sizeDataList).random()
                val newItem: ItemModel
                if (position < positionFavorite) {
                    newItem = ItemModel(R.drawable.friends_1, "Friends are listening", "54 tracks")
                    positionFavorite++
                    positionCategory++
                } else if (position < positionCategory) {
                    newItem = ItemModel(R.drawable.book, "Books", "")
                    positionCategory++
                } else {
                    newItem = ItemModel(R.drawable.the_neighbourhood, "The Neighbourhood", "68 tracks")
                }
                dataList.add(position, newItem)
            }
        }

        with(binding) {
            rvMusic.adapter = rvSongsViewTypeAdapter
            rvMusic.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("TEST TAG - MainFragment onDestroy")
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
        const val MAIN_FRAGMENT_TAG = "MAIN_FRAGMENT_TAG"
        fun getInstance() = MainFragment()
    }
}