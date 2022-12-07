package com.example.task8

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.task8.databinding.FragmentMainBinding

class MainFragment: Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val permission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            Toast.makeText(requireContext(), "Start", Toast.LENGTH_SHORT).show()
            val locationService = LocationService()
            val intentService = Intent(context, locationService.javaClass)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                activity?.startForegroundService(intentService)
            }
            Toast.makeText(requireContext(), "Start", Toast.LENGTH_SHORT).show()

        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Требуется разрешение")
                    .setMessage("Если вы хотите использовать геолокацию в этом приложении, дайте разрешение")
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
        _binding = FragmentMainBinding.bind(view)

        with(binding) {
            btnStart.setOnClickListener {
                permission.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            }
            btnStop.setOnClickListener {
                val locationService = LocationService()
                val intentService = Intent(requireContext(), locationService::class.java)
                activity?.stopService(intentService)
                Toast.makeText(requireContext(), "Stop", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
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

}