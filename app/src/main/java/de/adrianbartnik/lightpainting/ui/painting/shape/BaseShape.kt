package de.adrianbartnik.lightpainting.ui.painting.shape

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View


@SuppressLint("ViewConstructor")
open class BaseShape(context: Context, color: Int, val animating: Boolean = false) : View(context) {

    val paint = Paint()

    private var color: Int
    private val black = Paint()
    private var turnBlack = false
    private var state = 0

    init {
        this.color = color
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

    private fun generateNextColor(color: Int): Int {

        val amount = 15

        var r = color shr 16 and 0xff
        var g = color shr 8 and 0xff
        var b = color and 0xff

        when (state) {
            0 -> {
                g += amount
                if (g >= 255) {
                    g = 255
                    state = 1
                }
            }
            1 -> {
                r -= amount
                if (r <= 0) {
                    r = 0
                    state = 2
                }
            }
            2 -> {
                b += amount
                if (b >= 255) {
                    b = 255
                    state = 3
                }
            }
            3 -> {
                g -= amount
                if (g <= 0) {
                    g = 0
                    state = 4
                }
            }
            4 -> {
                r += amount
                if (r >= 255) {
                    r = 255
                    state = 5
                }
            }
            5 -> {
                b -= amount
                if (b <= 0) {
                    b = 0
                    state = 0
                }
            }
        }

        return (255 shl 24) + (r shl 16) + (g shl 8) + b
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (animating) {
            color = generateNextColor(color)
            paint.color = color

            invalidate()
        }
    }

    fun checkForTurnBlack(canvas: Canvas): Boolean {

        if (turnBlack) {
            canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), black)
        }

        return turnBlack
    }
}