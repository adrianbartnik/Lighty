package de.adrianbartnik.lightpainting.ui.painting.shape

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas

@SuppressLint("ViewConstructor")
class Bar(context: Context, rgbColor: Triple<Int, Int, Int>) : BaseShape(context, rgbColor) {

    companion object {
        private val WIDTH_FACTOR = 0.8f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (checkForTurnBlack(canvas)) {
            return
        }

        canvas.drawRect(width / 2f * WIDTH_FACTOR, 0f, width / 2f * (2 - WIDTH_FACTOR), height.toFloat(), paint)
    }
}
