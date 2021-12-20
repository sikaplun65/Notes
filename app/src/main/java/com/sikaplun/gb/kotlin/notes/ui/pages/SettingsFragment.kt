package com.sikaplun.gb.kotlin.notes.ui.pages

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.sikaplun.gb.kotlin.notes.R
import com.sikaplun.gb.kotlin.notes.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val requestLocationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
        ::onGotLocationPermissionResult
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.geolocationPermissionRequestButton.setOnClickListener {
            requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun onGotLocationPermissionResult(granted: Boolean) {
        if (granted) {
            onLocationPermissionGranted()
        } else {
            promptUserToOpenApplicationSettings()
        }

    }

    private fun onLocationPermissionGranted() = AlertDialog.Builder(requireActivity())
        .setMessage(R.string.location_permission_granted)
        .create()
        .show()

    private fun promptUserToOpenApplicationSettings() = AlertDialog.Builder(requireActivity())
        .setTitle(R.string.text_warning)
        .setMessage(R.string.text_explanation_for_connection)
        .setPositiveButton(R.string.text_yes) { _, _ ->
            val dialogIntent = Intent(Settings.ACTION_SETTINGS)
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(dialogIntent)
        }
        .setNegativeButton(R.string.text_no) { _, _ ->
            requireActivity().onBackPressed()
        }
        .create()
        .show()

}


