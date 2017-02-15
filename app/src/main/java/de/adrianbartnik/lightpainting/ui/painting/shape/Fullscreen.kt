package de.adrianbartnik.lightpainting.ui.painting.shape

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas


@SuppressLint("ViewConstructor")
class Fullscreen(context: Context, color: Int, animating : Boolean = false) : BaseShape(context, color, animating) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (checkForTurnBlack(canvas)) {
            return
        }

        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    }
}
