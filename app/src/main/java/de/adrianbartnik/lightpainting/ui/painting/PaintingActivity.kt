package de.adrianbartnik.lightpainting.ui.painting


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.adrianbartnik.lightpainting.ui.colorselection.ColorSelectionActivity
import de.adrianbartnik.lightpainting.ui.painting.shape.Fullscreen

class PaintingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(Fullscreen(this, Triple(11, 244, 234)))

        val shape = intent.extras?.containsKey(EXTRA_SHAPE) ?: false

        if (!shape) {
            finish()
            return
        }
    }

    companion object {

        private val EXTRA_SHAPE = "shape"

        fun GetStartIntent(context: Context, shape: ColorSelectionActivity.PaintShape): Intent {
            val intent = Intent(context, PaintingActivity::class.java)
            intent.putExtra(EXTRA_SHAPE, shape)
            return intent
        }
    }
}
