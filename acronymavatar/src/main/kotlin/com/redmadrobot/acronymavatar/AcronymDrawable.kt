package com.redmadrobot.acronymavatar

import android.graphics.*
import android.graphics.drawable.Drawable


class AcronymDrawable(private val acronym: String, backgroundColor: Int, private val sizeFactor: Float) : Drawable() {

    private val paintBackground = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintText = Paint(Paint.ANTI_ALIAS_FLAG)

    private val boundsRect = RectF()

    init {
        setColors(backgroundColor)
    }

    override fun draw(canvas: Canvas?) {
        canvas?.let {
            boundsRect.set(bounds)

            paintText.textSize = bounds.height() / sizeFactor

            val radius = Math.min(boundsRect.width(), boundsRect.height()) / 2
            it.drawCircle(boundsRect.centerX(), boundsRect.centerY(), radius, paintBackground)

            val yTextPosition = boundsRect.centerY() - paintText.ascent() / 2 - paintText.descent() / 2
            it.drawText(acronym, boundsRect.centerX(), yTextPosition, paintText)
        }
    }

    override fun setAlpha(alpha: Int) {
        paintText.alpha = alpha
        paintBackground.alpha = alpha
    }

    override fun getOpacity() = PixelFormat.UNKNOWN

    override fun setColorFilter(colorFilter: ColorFilter?) {
        /* Not used */
    }

    private fun setColors(backgroundColor: Int, textColor: Int = 0xFFFFFFFF.toInt()) {
        paintBackground.color = backgroundColor

        with(paintText) {
            color = textColor
            textAlign = Paint.Align.CENTER
        }
    }
}
