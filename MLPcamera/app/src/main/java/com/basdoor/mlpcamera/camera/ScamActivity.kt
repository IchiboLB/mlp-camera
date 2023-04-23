package com.basdoor.mlpcamera.camera

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.basdoor.mlpcamera.R
import com.google.common.util.concurrent.ListenableFuture

class ScamActivity : AppCompatActivity() {
    private lateinit var camProvider: ListenableFuture<ProcessCameraProvider>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scam)
        camProvider = ProcessCameraProvider.getInstance(this)
        camProvider.addListener({



        }, ContextCompat.getMainExecutor(this))
    }
}