package com.example.task_10.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.task_10.R
import com.example.task_10.databinding.FragmentRegisterBinding
import com.example.task_10.db.DatabaseHandler
import com.example.task_10.db.model.SettingModel
import com.example.task_10.db.model.UserModel
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.roundToInt
import kotlin.random.Random

class RegisterFragment: Fragment(R.layout.fragment_register) {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegisterBinding.bind(view)

        with(binding) {
            btnRegister.setOnClickListener {
                val preferences: SharedPreferences = requireActivity()
                    .getSharedPreferences("preferences", Context.MODE_PRIVATE)
                val editPreferences: SharedPreferences.Editor = preferences.edit()
                val id: Int = (Math.random() * 10000000 + 1).roundToInt()
                lifecycleScope.launch {
                    val regPass = "^[a-zA-Z0-9]{8,20}$".toRegex()
                    val regName = "^[a-zA-Z0-9]{2,20}$".toRegex()
                    if (regPass.matchEntire(etPassword.text.toString()) != null &&
                        regName.matchEntire(etUsername.text.toString()) != null) {
                        val username: String? = DatabaseHandler.getUsername(etUsername.text.toString())
                        if (username == null) {
                            editPreferences.putString("username", etUsername.text.toString())
                            editPreferences.apply()
                        } else {
                            etUsername.setText(preferences.getString("username", "").toString())
                        }
                        val user: UserModel
                        val settings: SettingModel
                        if (preferences.getString("username", "") == etUsername.text.toString()) {
                            user = UserModel(
                                id = id,
                                username = etUsername.text.toString(),
                                password = etPassword.text.toString()
                            )
                            settings = SettingModel(
                                idUser = id,
                                settingOne = 1,
                                settingTwo = 1,
                                settingThree = 1
                            )
                            editPreferences.putInt("id", id)
                            editPreferences.putInt("set-1", 1)
                            editPreferences.putInt("set-2", 1)
                            editPreferences.putInt("set-3", 1)
                            editPreferences.apply()

                            DatabaseHandler.createUser(user)
                            DatabaseHandler.createSettings(settings)

                            findNavController().navigate(
                                R.id.action_registerFragment_to_profileFragment
                            )

                        } else {
                            Toast.makeText(
                                activity,
                                "This username already exists",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            activity,
                            "Password/Username entered incorrectly (a-zA-Z0-9, password 8-20 symb, username 2-20 sumb)",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
        println("TEST TAG - RegisterFragment onDestroy")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - RegisterFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - RegisterFragment onPause")
    }
}