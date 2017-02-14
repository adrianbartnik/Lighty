package de.adrianbartnik.lightpainting.ui.painting.shape

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View


@SuppressLint("ViewConstructor")
open class BaseShape(context: Context, color: Int) : View(context) {

    val paint = Paint()
    val black = Paint()
    var turnBlack = false

    init {
        paint.setARGB(255, Color.red(color), Color.green(color), Color.blue(color))
        paint.strokeWidth = 8f
        paint.isAntiAlias = true
        paint.strokeCap = Paint.Cap.BUTT
        paint.style = Paint.Style.FILL

        black.style = Paint.Style.FILL
        black.isAntiAlias = true
        black.strokeWidth = 8f
        black.setARGB(255, 0, 0, 0)

        setOnClickListener {
            turnBlack = !turnBlack
            invalidate()
        }
    }

    fun checkForTurnBlack(canvas: Canvas) : Boolean {

        if (turnBlack) {
            canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), black)
        }

        return turnBlack
    }
}