package com.example.task_10.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.util.UUIDUtil
import com.example.task_10.R
import com.example.task_10.databinding.FragmentEntryBinding
import com.example.task_10.db.DatabaseHandler
import com.example.task_10.db.entity.UserEntity
import com.example.task_10.db.mapper.UserMapper
import com.example.task_10.db.model.SettingModel
import com.example.task_10.db.model.UserModel
import kotlinx.coroutines.launch
import java.util.*

class EntryFragment: Fragment(R.layout.fragment_entry) {

    private var _binding: FragmentEntryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEntryBinding.bind(view)

        with(binding) {
            btnLogin.setOnClickListener {
                val preferences: SharedPreferences = requireActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE)
                val editPreferences: SharedPreferences.Editor = preferences.edit()
                var user: UserModel?
                lifecycleScope.launch {
                    user = DatabaseHandler.getUser(etUsername.text.toString(), etPassword.text.toString())
                    val id: Int? = user?.id
                    val username: String? = user?.username
                    val password: String? = user?.password
                    if (id != null && username != null && password != null) {
                        editPreferences.putString("username", username)
                        editPreferences.putInt("id", id)
                        editPreferences.apply()
                        var settings: SettingModel? = DatabaseHandler.getSettings(
                            preferences.getInt("id", 0)
                        )
                        val setting1: Int? = settings?.settingOne
                        val setting2: Int? = settings?.settingTwo
                        val setting3: Int? = settings?.settingThree
                        if (setting1 != null && setting2 != null && setting3 != null) {
                            editPreferences.putInt("set-1", setting1)
                            editPreferences.putInt("set-2", setting2)
                            editPreferences.putInt("set-3", setting3)
                            editPreferences.apply()
                        }
                        findNavController().navigate(
                            R.id.action_entryFragment_to_profileFragment
                        )
                    } else {
                        Toast.makeText(
                            activity,
                            "Invalid username or password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            btnRegister.setOnClickListener {
                findNavController().navigate(
                    R.id.action_entryFragment_to_registerFragment
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
        println("TEST TAG - EntryFragment onDestroy")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - EntryFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - EntryFragment onPause")
    }
}