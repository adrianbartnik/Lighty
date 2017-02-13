package de.adrianbartnik.lightpainting.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import de.adrianbartnik.lightpainting.R
import de.adrianbartnik.lightpainting.ui.colorselection.ColorSelectionActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionbar()

        findViewById(R.id.activity_main_fullscreen).setOnClickListener {
            startActivity(ColorSelectionActivity.GetStartIntent(application, ColorSelectionActivity.PaintShape.FullscreenShape))
        }

        findViewById(R.id.activity_main_circle).setOnClickListener {
            startActivity(ColorSelectionActivity.GetStartIntent(application, ColorSelectionActivity.PaintShape.CircleShape))
        }

        findViewById(R.id.activity_main_bar).setOnClickListener {
            startActivity(ColorSelectionActivity.GetStartIntent(application, ColorSelectionActivity.PaintShape.BarShape))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_game -> {
                Toast.makeText(this, "Holla", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun setupActionbar() {
        val myToolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(myToolbar)

        val actionbar = supportActionBar
        actionbar?.setDisplayShowTitleEnabled(false)
    }
}
