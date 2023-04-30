package com.basdoor.mlpcamera.helpers

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class FrameAnalyzer : ImageAnalysis.Analyzer {
    override fun analyze(image: ImageProxy) {
        println("fghjk")
        image.close()
    }

}