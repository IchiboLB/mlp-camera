package com.basdoor.mlpcamera.helpers

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.basdoor.mlpcamera.camera.LandmarkView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetector
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions

@ExperimentalGetImage
class FrameAnalyzer(
    private val viewPoint: LandmarkView
) : ImageAnalysis.Analyzer {
    private val options = PoseDetectorOptions.Builder().setDetectorMode(PoseDetectorOptions.STREAM_MODE)
        .build()
    private val detector = PoseDetection.getClient(options)
    override fun analyze(image: ImageProxy) {
        val mediaImage = image.image
        if(mediaImage != null) {
           val imageForDetector = InputImage.fromMediaImage(mediaImage,image.imageInfo.rotationDegrees)
            detector.process(imageForDetector)
                .addOnSuccessListener {
                    resultPose -> viewPoint.setParameters(resultPose)
            }
                .addOnFailureListener{
                    println("цель утекла")
                }
        }
        image.close()
    }

}