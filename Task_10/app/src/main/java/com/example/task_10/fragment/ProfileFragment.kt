package com.example.task_10.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.task_10.R
import com.example.task_10.databinding.FragmentProfileBinding
import com.example.task_10.db.DatabaseHandler
import com.example.task_10.db.model.UserUpdateModel
import kotlinx.coroutines.launch

class ProfileFragment: Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)
        val preferences: SharedPreferences = requireActivity()
            .getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editPreferences: SharedPreferences.Editor = preferences.edit()
        with(binding) {
            val username = preferences.getString("username", "")
            if (username != "") {
                etUsername.setText(username)
            }
            btnEditUsername.setOnClickListener {
                etUsername.isEnabled = true
            }
            btnSave.setOnClickListener {
                etUsername.isEnabled = false
                lifecycleScope.launch {
                    val regName = "^[a-zA-Z0-9]{2,20}$".toRegex()
                    if (regName.matchEntire(etUsername.text.toString()) != null) {
                        val username: String? = DatabaseHandler.getUsername(etUsername.text.toString())
                        if (username == null) {
                            editPreferences.putString("username", etUsername.text.toString())
                            editPreferences.apply()
                        } else {
                            etUsername.setText(preferences.getString("username", "").toString())
                            if (username != preferences.getString("username", "")) {
                                Toast.makeText(
                                    activity,
                                    "This username already exists",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        val user = UserUpdateModel(
                            preferences.getInt("id", 0),
                            preferences.getString("username", "").toString()
                        )
                        DatabaseHandler.updateUser(user)
                    } else {
                        etUsername.setText(preferences.getString("username", "").toString())
                        Toast.makeText(
                            activity,
                            "Username entered incorrectly (a-zA-Z0-9, 2-20 symb)",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            }
            btnExit.setOnClickListener {
                editPreferences.clear()
                editPreferences.apply()
                findNavController().navigate(
                    R.id.action_profileFragment_to_entryFragment
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
        println("TEST TAG - ProfileFragment onDestroy")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - ProfileFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - ProfileFragment onPause")
    }
}