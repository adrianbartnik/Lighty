package de.adrianbartnik.lightpainting.ui.painting.shape

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View


@SuppressLint("ViewConstructor")
class Fullscreen(context: Context, rgbColor: Triple<Int, Int, Int>) : View(context) {

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

        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    }
}
