package de.adrianbartnik.lightpainting.ui.colorselection


import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.FrameLayout
import com.android.colorpicker.ColorPickerPalette
import com.android.colorpicker.ColorPickerSwatch
import com.pes.androidmaterialcolorpickerdialog.ColorPicker
import de.adrianbartnik.lightpainting.R

class ColorSelectionActivity : AppCompatActivity() {

    lateinit var colorPickerPalette : ColorPickerPalette

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_selection)

        setupActionbar()

        colorPickerPalette = findViewById(R.id.picker) as ColorPickerPalette

        val colors = resources.getIntArray(R.array.color_palette)

        colorPickerPalette.init(5)
        colorPickerPalette.drawPalette(colors, colors[0])

        setupCustomColor()
    }

    private fun setupCustomColor() {
        val defaultColor = ContextCompat.getColor(applicationContext, R.color.blue_grey)
        val view = ColorPickerSwatch(this@ColorSelectionActivity, defaultColor, false, null)

        val framelayout = findViewById(R.id.color_selection_custom_color) as FrameLayout
        framelayout.addView(view)

        view.setOnClickListener {
            val defaultRed = resources.getInteger(R.integer.default_red)
            val defaulGreen = resources.getInteger(R.integer.default_green)
            val defaultBlue = resources.getInteger(R.integer.default_blue)

            val cp = ColorPicker(this@ColorSelectionActivity, defaultRed, defaulGreen, defaultBlue)
            cp.setCanceledOnTouchOutside(true)
            cp.setCancelable(true)
            cp.show()

            cp.setOnColorSelected { col ->
                view.setColor(col)
                view.setChecked(true)
                colorPickerPalette.clearCurrentSelection()
                cp.dismiss()
            }
        }
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

        private val TAG = ColorSelectionActivity::class.java.simpleName

        fun GetStartIntent(context: Context): Intent {
            return Intent(context, ColorSelectionActivity::class.java)
        }
    }
}
