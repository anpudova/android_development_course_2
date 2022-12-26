package com.example.task_10.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.task_10.R
import com.example.task_10.databinding.FragmentSettingsBinding
import com.example.task_10.db.DatabaseHandler
import com.example.task_10.db.entity.SettingEntity
import kotlinx.coroutines.launch

class SettingsFragment: Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)

        val preferences: SharedPreferences = requireActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editPreferences: SharedPreferences.Editor = preferences.edit()

        with(binding) {
            cbOne.isChecked = preferences.getInt("set-1", 1) == 1
            cbTwo.isChecked = preferences.getInt("set-2", 1) == 1
            cbThree.isChecked = preferences.getInt("set-3", 1) == 1

            cbOne.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    editPreferences.putInt("set-1", 1)
                } else {
                    editPreferences.putInt("set-1", 2)
                }
                editPreferences.apply()
            }
            cbTwo.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    editPreferences.putInt("set-2", 1)
                } else {
                    editPreferences.putInt("set-2", 2)
                }
                editPreferences.apply()
            }
            cbThree.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    editPreferences.putInt("set-3", 1)
                } else {
                    editPreferences.putInt("set-3", 2)
                }
                editPreferences.apply()
            }
            btnSave.setOnClickListener {
                lifecycleScope.launch {
                    val settings = SettingEntity(
                        preferences.getInt("id", 0),
                        preferences.getInt("set-1", 1),
                        preferences.getInt("set-2", 1),
                        preferences.getInt("set-3", 1))
                    DatabaseHandler.updateSettings(settings)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
        println("TEST TAG - SettingsFragment onDestroy")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - SettingsFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - SettingsFragment onPause")
    }
}