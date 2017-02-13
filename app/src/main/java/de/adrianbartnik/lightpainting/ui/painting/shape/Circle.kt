package de.adrianbartnik.lightpainting.ui.painting.shape


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View

@SuppressLint("ViewConstructor")
class Circle(context: Context, rgbColor: Triple<Int, Int, Int>) : View(context) {

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

        val minSize = Math.min(width, height) / 2

        canvas.drawCircle(width / 2f, height / 2f, minSize * 0.8f, paint)
    }
}
