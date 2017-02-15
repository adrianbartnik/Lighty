package de.adrianbartnik.lightpainting.ui.painting.shape


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas

@SuppressLint("ViewConstructor")
class Circle(context: Context, color: Int, animating : Boolean = false) : BaseShape(context, color, animating) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (checkForTurnBlack(canvas)) {
            return
        }

        val minSize = Math.min(width, height) / 2

        canvas.drawCircle(width / 2f, height / 2f, minSize * 0.8f, paint)
    }
}
