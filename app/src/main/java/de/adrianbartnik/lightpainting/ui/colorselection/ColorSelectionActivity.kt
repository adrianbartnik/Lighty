package de.adrianbartnik.lightpainting.ui.colorselection


import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.FrameLayout
import com.android.colorpicker.ColorPickerPalette
import com.android.colorpicker.ColorPickerSwatch
import com.pes.androidmaterialcolorpickerdialog.ColorPicker
import de.adrianbartnik.lightpainting.R
import de.adrianbartnik.lightpainting.layout.RevealLayout
import de.adrianbartnik.lightpainting.ui.painting.PaintingActivity


class ColorSelectionActivity : AppCompatActivity() {

    lateinit var colorPickerPalette : ColorPickerPalette
    lateinit var mFab : FloatingActionButton
    var currentColor : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_selection)

        setupActionbar()
        setupFAB()
        setupColorpalette()
        setupCustomColor()
    }

    private fun setupColorpalette() {
        colorPickerPalette = findViewById(R.id.picker) as ColorPickerPalette

        val colors = resources.getIntArray(R.array.color_palette)

        colorPickerPalette.init(5)
        colorPickerPalette.drawPalette(colors, colors[0])
    }

    private fun setupFAB() {

        mFab = findViewById(R.id.fab) as FloatingActionButton
        val mRevealLayout = findViewById(R.id.reveal_layout) as RevealLayout
        val mRevealView = findViewById(R.id.reveal_view)

        mFab.setOnClickListener {
            mFab.isClickable = false // Avoid naughty guys clicking FAB again and again...
            val location = IntArray(2)
            mFab.getLocationOnScreen(location)
            location[0] += mFab.getWidth() / 2
            location[1] += mFab.getHeight() / 2

            val col = colorPickerPalette.currentSelectedColor?.color ?: currentColor

            val intent = PaintingActivity.GetStartIntent(this@ColorSelectionActivity, PaintShape.FullscreenShape, col)

            mRevealView.setVisibility(View.VISIBLE)
            mRevealView.setBackgroundColor(col)
            mRevealLayout.setVisibility(View.VISIBLE)

            mRevealLayout.show(location[0], location[1]) // Expand from center of FAB. Actually, it just plays reveal animation.

            mFab.postDelayed({
                startActivity(intent)
                 // Without using R.anim.hold, the screen will flash because of transition of Activities.
                overridePendingTransition(0, R.anim.hold)
            }, 600) // 600 is default duration of reveal animation in RevealLayout

            mFab.postDelayed({
                mFab.setClickable(true)
                mRevealLayout.setVisibility(View.INVISIBLE)
                mRevealView.setVisibility(View.INVISIBLE)
            }, 960) // Or some numbers larger than 600.
        }
    }

    private fun setupCustomColor() {
        setCurrentColder(ContextCompat.getColor(applicationContext, R.color.blue_grey))
        val view = ColorPickerSwatch(this@ColorSelectionActivity, currentColor, false, null)

        val framelayout = findViewById(R.id.color_selection_custom_color) as FrameLayout
        framelayout.addView(view)

        view.setOnClickListener {
            val default = ContextCompat.getColor(this, R.color.default_color_colorpicker)

            val cp = ColorPicker(this@ColorSelectionActivity, Color.red(default), Color.green(default), Color.blue(default))
            cp.setCanceledOnTouchOutside(true)
            cp.setCancelable(true)
            cp.show()

            cp.setOnColorSelected { col ->
                view.setColor(col)
                view.setChecked(true)
                colorPickerPalette.clearCurrentSelection()
                setCurrentColder(col)
                cp.dismiss()
            }
        }
    }

    private fun setCurrentColder(col: Int) {
        currentColor = col
        mFab.backgroundTintList = ColorStateList.valueOf(col)
        mFab.rippleColor = col
    }

    private fun setupActionbar() {
        val myToolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(myToolbar)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayShowTitleEnabled(false)

        val upArrow = ContextCompat.getDrawable(application, R.drawable.abc_ic_ab_back_material)
        upArrow.setColorFilter(ContextCompat.getColor(application, R.color.upindicator_color), PorterDuff.Mode.SRC_ATOP)
        actionbar?.setHomeAsUpIndicator(upArrow)
    }

    companion object {

        private val EXTRA_SHAPE = "color_selection_shape"

        fun GetStartIntent(context: Context, shape: PaintShape): Intent {
            val intent = Intent(context, ColorSelectionActivity::class.java)
            intent.putExtra(EXTRA_SHAPE, shape)
            return intent
        }
    }

    enum class PaintShape {
        CircleShape, FullscreenShape, BarShape
    }
}
