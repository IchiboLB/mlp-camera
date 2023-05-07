package com.basdoor.mlpcamera.camera

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.basdoor.mlpcamera.R
import com.basdoor.mlpcamera.helpers.FrameAnalyzer
import com.google.common.util.concurrent.ListenableFuture

@ExperimentalGetImage class ScamActivity : AppCompatActivity() {
    private lateinit var camProvider: ListenableFuture<ProcessCameraProvider>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scam)
        camProvider = ProcessCameraProvider.getInstance(this)
        camProvider.addListener({
        val provider = camProvider.get()
        camBind(provider)
        }, ContextCompat.getMainExecutor(this))
    }

    private fun camBind(cameraProvider: ProcessCameraProvider) {
        val cameraSelector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_FRONT)
            .build()
        val preview = Preview.Builder()
            .build()
        val previewCam = findViewById<PreviewView>(R.id.preview_cam)
        preview.setSurfaceProvider(previewCam.surfaceProvider)
        val imageAnalyzer = ImageAnalysis.Builder().setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
        val viewPoint = findViewById<LandmarkView>(R.id.keyframes)
        imageAnalyzer.setAnalyzer(mainExecutor, FrameAnalyzer(viewPoint))
        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalyzer)
    }
}