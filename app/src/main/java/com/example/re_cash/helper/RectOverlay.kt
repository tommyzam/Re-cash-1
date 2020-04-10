package com.example.re_cash.helper
/**
import android.graphics.*

class RectOverlay internal constructor(overlay: GraphicOverlay, private val bound: Rect?): GraphicOverlay.Graphic(overlay) {
    private val rectPaint:Paint

    init {
        rectPaint = Paint()
        rectPaint.color = RECT_COLOR
        rectPaint.strokeWidth = STROKE_WIDTH
        rectPaint.style = Paint.Style.STROKE

        postInvalidate()
    }

    companion object{
        private val RECT_COLOR = Color.YELLOW
        private val STROKE_WIDTH = 8.0f
    }
    override fun draw(canvas: Canvas?) {
        val rect = RectF(bound)
        canvas?.drawRect(rect,rectPaint)

    }
}*/