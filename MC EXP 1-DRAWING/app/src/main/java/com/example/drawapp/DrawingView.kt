package com.example.drawapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var paint = Paint()
    private val path = Path()

    init {
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 8f
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> path.lineTo(x, y)
            MotionEvent.ACTION_UP -> {}
        }

        invalidate() // Redraw the view
        return true
    }

    fun clearCanvas() {
        path.reset()
        invalidate()
    }

    fun changeColor(newColor: Int) {
        paint.color = newColor
    }
    fun changeBrushSize(newSize: Float) {
        paint.strokeWidth = newSize
    }

}
