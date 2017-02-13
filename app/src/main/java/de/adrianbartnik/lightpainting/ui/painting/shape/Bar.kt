package de.adrianbartnik.lightpainting.ui.painting.shape

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View

@SuppressLint("ViewConstructor")
class Bar(context: Context, rgbColor: Triple<Int, Int, Int>) : View(context) {

    companion object {
        private val WIDTH_FACTOR = 0.8f
    }

    val paint = Paint()

    init {
        paint.setARGB(255, rgbColor.first, rgbColor.second, rgbColor.third)
        paint.strokeWidth = 8f
        paint.isAntiAlias = true
        paint.strokeCap = Paint.Cap.BUTT
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawRect(width / 2f * WIDTH_FACTOR, 0f, width / 2f * (2 - WIDTH_FACTOR), height.toFloat(), paint)
    }
}
