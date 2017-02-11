package de.adrianbartnik.lightpainting.ui.painting


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class PaintingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shape = intent.extras?.containsKey(EXTRA_SHAPE) ?: false

        if (!shape) {
            finish()
            return
        }
    }

    enum class PaintShape {
        Circle, Bar, Fullscreen, Cross
    }

    companion object {

        private val EXTRA_SHAPE = "shape"

        fun GetStartIntent(context: Context, shape: PaintShape): Intent {
            val intent = Intent(context, PaintingActivity::class.java)
            intent.putExtra(EXTRA_SHAPE, shape)
            return intent
        }
    }
}
