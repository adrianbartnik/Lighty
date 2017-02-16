package de.adrianbartnik.lightpainting.ui.painting


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.adrianbartnik.lightpainting.ui.colorselection.ColorSelectionActivity
import de.adrianbartnik.lightpainting.ui.painting.shape.Bar
import de.adrianbartnik.lightpainting.ui.painting.shape.Circle
import de.adrianbartnik.lightpainting.ui.painting.shape.Fullscreen

class PaintingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shape = intent.extras.getSerializable(EXTRA_SHAPE) as ColorSelectionActivity.PaintShape
        val col =  intent.extras.getInt(EXTRA_COLOR, 0xffffffff.toInt())
        val animating =  intent.extras.getBoolean(EXTRA_ANIMATING, false)

        val view = when (shape) {
            ColorSelectionActivity.PaintShape.BarShape -> Bar(this, col, animating)
            ColorSelectionActivity.PaintShape.FullscreenShape -> Fullscreen(this, col, animating)
            ColorSelectionActivity.PaintShape.CircleShape -> Circle(this, col, animating)
        }

        setContentView(view)
    }

    companion object {

        private val EXTRA_SHAPE = "painting_activity_shape"
        private val EXTRA_COLOR = "painting_activity_color"
        private val EXTRA_ANIMATING = "painting_activity_color"

        fun GetStartIntent(context: Context, shape: ColorSelectionActivity.PaintShape, color: Int): Intent {
            val intent = Intent(context, PaintingActivity::class.java)
            intent.putExtra(EXTRA_SHAPE, shape)
            intent.putExtra(EXTRA_COLOR, color)
            return intent
        }

        fun GetStartIntent(context: Context, shape: ColorSelectionActivity.PaintShape, animating: Boolean): Intent {
            val intent = Intent(context, PaintingActivity::class.java)
            intent.putExtra(EXTRA_SHAPE, shape)
            intent.putExtra(EXTRA_ANIMATING, animating)
            return intent
        }
    }
}
