package com.basdoor.mlpcamera.camera

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Size
import android.view.View
import com.google.mlkit.vision.common.PointF3D
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark
import kotlinx.coroutines.MainScope

class LandmarkView(
    context: Context,
    attributes: AttributeSet
) : View(context, attributes) {
    private var viewSize = Size(0,0)
    private val mainKist = Paint(ANTI_ALIAS_FLAG)
    private var detectedPose: Pose? = null
    private var sizeSource = Size(0,0)
    init {
        mainKist.color = Color.MAGENTA
        mainKist.strokeWidth = 6F
        mainKist.style = Paint.Style.FILL
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewSize = Size(w, h)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawPoints(canvas)
        drawLines(canvas)
    }

    fun setParameters(pose: Pose, sourceSize: Size) {
        detectedPose = pose
        invalidate()
        sizeSource = sourceSize
    }

    private fun drawLandmark(landmark: PoseLandmark, drawCanvas: Canvas?) {
        val position = convertPoint(landmark.position3D)

        drawCanvas?.drawCircle(position.x, position.y,10F, mainKist)
    }

    private fun convertPoint(target: PointF3D): PointF {
        val x1 = target.x
        val y1 = target.y
        val w1 = sizeSource.width
        val h1 = sizeSource.height
        val w2 = viewSize.width
        val h2 = viewSize.height

        val x2 = x1*w2/w1
        val y2 = y1*h2/h1
        return PointF(x2, y2)
    }

    private fun drawPoints(canvas: Canvas?) {
        var landmark = detectedPose?.getPoseLandmark(PoseLandmark.NOSE)
        landmark?.let {
            drawLandmark(it, canvas)
        }
        landmark = detectedPose?.getPoseLandmark((PoseLandmark.LEFT_EYE_INNER))
        landmark?.let {
            drawLandmark(it, canvas)
        }
        landmark = detectedPose?.getPoseLandmark((PoseLandmark.LEFT_EYE_OUTER))
        landmark?.let {
            drawLandmark(it, canvas)
        }
        landmark = detectedPose?.getPoseLandmark((PoseLandmark.LEFT_EYE))
        landmark?.let {
            drawLandmark(it, canvas)
        }
        landmark = detectedPose?.getPoseLandmark((PoseLandmark.RIGHT_EYE_INNER))
        landmark?.let {
            drawLandmark(it, canvas)
        }
        landmark = detectedPose?.getPoseLandmark((PoseLandmark.RIGHT_EYE_OUTER))
        landmark?.let {
            drawLandmark(it, canvas)
        }
        landmark = detectedPose?.getPoseLandmark((PoseLandmark.RIGHT_EYE))
        landmark?.let {
            drawLandmark(it, canvas)
        }
        landmark = detectedPose?.getPoseLandmark((PoseLandmark.RIGHT_SHOULDER))
        landmark?.let {
            drawLandmark(it, canvas)
        }
        landmark = detectedPose?.getPoseLandmark((PoseLandmark.LEFT_SHOULDER))
        landmark?.let {
            drawLandmark(it, canvas)
        }
        landmark = detectedPose?.getPoseLandmark((PoseLandmark.RIGHT_ELBOW))
        landmark?.let {
            drawLandmark(it, canvas)
        }
        landmark = detectedPose?.getPoseLandmark((PoseLandmark.LEFT_ELBOW))
        landmark?.let {
            drawLandmark(it, canvas)
        }
        landmark = detectedPose?.getPoseLandmark((PoseLandmark.LEFT_MOUTH))
        landmark?.let {
            drawLandmark(it, canvas)
        }
        landmark = detectedPose?.getPoseLandmark((PoseLandmark.RIGHT_MOUTH))
        landmark?.let {
            drawLandmark(it, canvas)
        }
    }

    private fun drawLines(canvas: Canvas?) {
        var landmark1 = detectedPose?.getPoseLandmark(PoseLandmark.LEFT_MOUTH)
        var landmark2 = detectedPose?.getPoseLandmark(PoseLandmark.RIGHT_MOUTH)
        if(landmark1 != null && landmark2 != null) {
            drawSingleLine(landmark1, landmark2, canvas)
        }
        landmark1 = detectedPose?.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)
        landmark2 = detectedPose?.getPoseLandmark(PoseLandmark.LEFT_ELBOW)
        if(landmark1 != null && landmark2 != null) {
            drawSingleLine(landmark1, landmark2, canvas)
        }
        landmark1 = detectedPose?.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER)
        landmark2 = detectedPose?.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)
        if(landmark1 != null && landmark2 != null) {
            drawSingleLine(landmark1, landmark2, canvas)
        }
        landmark1 = detectedPose?.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER)
        landmark2 = detectedPose?.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)
        if(landmark1 != null && landmark2 != null) {
            drawSingleLine(landmark1, landmark2, canvas)
        }

    }

    private fun drawSingleLine(start: PoseLandmark, end: PoseLandmark, canvas: Canvas?) {
        val startPoint = convertPoint(start.position3D)
        val endPoint = convertPoint(end.position3D)
        canvas?.drawLine(
            startPoint.x,
            startPoint.y,
            endPoint.x,
            endPoint.y,
            mainKist
        )
    }

}