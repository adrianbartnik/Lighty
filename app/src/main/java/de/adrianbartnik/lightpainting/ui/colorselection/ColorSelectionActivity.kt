package de.adrianbartnik.lightpainting.ui.colorselection


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Button
import com.android.colorpicker.ColorPickerPalette
import com.pes.androidmaterialcolorpickerdialog.ColorPicker
import de.adrianbartnik.lightpainting.R

class ColorSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_selection)

        setupActionbar()

        val colorPickerPalette = findViewById(R.id.picker) as ColorPickerPalette

        val colors = resources.getIntArray(R.array.rainbow)

        colorPickerPalette.init(5)
        colorPickerPalette.drawPalette(colors, colors[0])

        val button = findViewById(R.id.button12) as Button

        button.setOnClickListener {
            val defaultRed = resources.getInteger(R.integer.default_red)
            val defaulGreen = resources.getInteger(R.integer.default_green)
            val defaultBlue = resources.getInteger(R.integer.default_blue)

            val cp = ColorPicker(this@ColorSelectionActivity, defaultRed, defaulGreen, defaultBlue)
            cp.setCanceledOnTouchOutside(true)
            cp.setCancelable(true)
            cp.show()

            cp.setOnColorSelected { col ->
                Log.d(TAG, Integer.toString(Color.red(col)))
                Log.d(TAG, Integer.toString(Color.green(col)))
                Log.d(TAG, Integer.toString(Color.blue(col)))

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
        upArrow.setColorFilter(ContextCompat.getColor(application, R.color.blue), PorterDuff.Mode.SRC_ATOP)
        actionbar?.setHomeAsUpIndicator(upArrow)
    }

    companion object {

        private val TAG = ColorSelectionActivity::class.java.simpleName

        fun GetStartIntent(context: Context): Intent {
            return Intent(context, ColorSelectionActivity::class.java)
        }
    }
}
