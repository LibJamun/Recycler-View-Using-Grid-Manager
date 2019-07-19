package com.jamun.vinsol.utils

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import androidx.core.content.ContextCompat
import com.jamun.vinsol.R
import kotlin.math.max


class BD(context: Context) : Drawable() {

    private val bP: Paint
    private val bP1: Paint
    private val bP2: Paint
    private val tP: Paint
    private val tR = Rect()

    private var mCount = ""
    private var mWillDraw: Boolean = false

    init {
        val mTextSize = context.resources.getDimension(R.dimen.badge_text_size)

        bP = Paint()
        bP.color = ContextCompat.getColor(context,R.color.colorPrimaryDark)
        bP.isAntiAlias = true
        bP.textSize = mTextSize
        bP.style = Paint.Style.FILL

        bP1 = Paint()
        bP1.color = ContextCompat.getColor(context, R.color.colorPrimaryDark)
        bP1.isAntiAlias = true
        bP1.style = Paint.Style.FILL
        bP1.textSize = mTextSize

        bP2 = Paint()
        bP2.color = ContextCompat.getColor(context,R.color.colorWhite)
        bP2.isAntiAlias = true
        bP2.textSize = mTextSize
        bP2.strokeWidth = 4f
        bP2.style = Paint.Style.STROKE

        tP = Paint()
        tP.color = ContextCompat.getColor(context,R.color.colorWhite)
        tP.typeface = Typeface.DEFAULT
        tP.textSize = mTextSize
        tP.typeface = Typeface.DEFAULT_BOLD
        tP.isAntiAlias = true
        tP.textAlign = Paint.Align.CENTER
    }

    override fun draw(canvas: Canvas) {
        if (!mWillDraw) {
            return
        }
        val bounds = bounds
        val width = (bounds.right - bounds.left).toFloat()
        val height = (bounds.bottom - bounds.top).toFloat()
        val radius = max(width, height) / 2 / 2
        val centerX = width - radius - 1f + 5
        val centerY = radius - 5
        if (mCount.length <= 2) {
            canvas.drawCircle(centerX, centerY, (radius + 5.5).toInt().toFloat(), bP2)
            canvas.drawCircle(centerX, centerY, (radius + 5.5).toInt().toFloat(), bP1)
            canvas.drawCircle(centerX, centerY, (radius + 3.5).toInt().toFloat(), bP)
        } else {
            canvas.drawCircle(centerX, centerY, (radius + 5.5).toInt().toFloat(), bP2)
            canvas.drawCircle(centerX, centerY, (radius + 5.5).toInt().toFloat(), bP1)
            canvas.drawCircle(centerX, centerY, (radius + 3.5).toInt().toFloat(), bP)
        }
        tP.getTextBounds(mCount, 0, mCount.length, tR)
        val textHeight = (tR.bottom - tR.top).toFloat()
        val textY = centerY + textHeight / 2f
        if (mCount.length > 2)
            canvas.drawText("99+", centerX, textY, tP)
        else
            canvas.drawText(mCount, centerX, textY, tP)
    }

    fun setCount(count: String) {
        mCount = count
        mWillDraw = !count.equals("0", ignoreCase = true)
        invalidateSelf()
    }

    override fun setAlpha(alpha: Int) {}

    override fun setColorFilter(cf: ColorFilter?) {}

    override fun getOpacity(): Int {
        return PixelFormat.UNKNOWN
    }

    companion object {

        fun setBadgeCount(context: Context, count: Int, id: Int, layerDrawable: LayerDrawable) {
            var badge = BD(context)
            val reuse = layerDrawable.findDrawableByLayerId(id)
            if (reuse is BD) {
                badge = reuse
            }
            badge.setCount(count.toString())
            layerDrawable.mutate()
            layerDrawable.setDrawableByLayerId(id, badge)
        }
    }
}
