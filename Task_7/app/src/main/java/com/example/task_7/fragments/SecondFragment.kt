package com.example.task_7.fragments

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.task_7.R
import com.example.task_7.databinding.FragmentSecondBinding

class SecondFragment: Fragment(R.layout.fragment_second) {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val permission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), android.Manifest.permission.CAMERA)) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Требуется разрешение")
                    .setMessage("Если вы хотите использовать камеру в этом приложении, дайте разрешение")
                    .show()
            } else {
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", requireActivity().packageName, null)
                )
                startActivity(intent)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSecondBinding.bind(view)

        with(binding) {
            btnOpenCamera.setOnClickListener {
                permission.launch(android.Manifest.permission.CAMERA)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
        println("TEST TAG - SecondFragment onDestroy")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - SecondFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - SecondFragment onPause")
    }
}