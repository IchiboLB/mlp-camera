package com.basdoor.mlpcamera.camera

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.util.Size
import android.view.View
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark

class LandmarkView(
    context: Context,
    attributes: AttributeSet
) : View(context, attributes) {
    private var viewSize = Size(0,0)
    private val mainKist = Paint(ANTI_ALIAS_FLAG)
    private var detectedPose: Pose? = null
    init {
        mainKist.color = Color.MAGENTA
        mainKist.strokeWidth = 4F
        mainKist.style = Paint.Style.FILL
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewSize = Size(w, h)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val landmark = detectedPose?.getPoseLandmark(PoseLandmark.NOSE)
        canvas?.drawCircle((viewSize.width/2).toFloat(), (viewSize.height/2).toFloat(), 30F, mainKist)
    }

    fun setParameters(pose: Pose) {
        detectedPose = pose
        invalidate()
    }
}