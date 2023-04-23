package com.basdoor.mlpcamera.scanstart

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.basdoor.mlpcamera.R
import com.basdoor.mlpcamera.camera.ScamActivity

class StartFragment : Fragment() {
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),

    ) { isGranted:Boolean ->
        if (isGranted == true) {
            startScam()
        } else {
            //domashnee zadanie: pri otkaze scrivat start scan knopku i pokazat soobschenie
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        
        super.onViewCreated(view, savedInstanceState)
        val startButton = view.findViewById<Button>(R.id.start)
        //startButton.isVisible (k dz)
        startButton.setOnClickListener {
        val permission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
        when(permission) {
            PackageManager.PERMISSION_GRANTED -> {startScam()}
            else -> {permissionLauncher.launch(Manifest.permission.CAMERA)}
        }
        }
    }
        private fun startScam() {
            val scamIntent = Intent(requireContext(), ScamActivity:: class.java)
            startActivity(scamIntent)
    }

}