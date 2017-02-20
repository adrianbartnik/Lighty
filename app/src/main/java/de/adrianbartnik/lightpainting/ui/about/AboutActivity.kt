package de.adrianbartnik.lightpainting.ui.about

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import de.adrianbartnik.lightpainting.BuildConfig
import de.adrianbartnik.lightpainting.R
import java.util.*


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_about)
        setupActionbar()
        setupVersion()
        setupButtons()
        setupLibraries()
    }

    private fun setupButtons() {
        findViewById(R.id.activity_about_button_github).setOnClickListener {
            openURLInBrowser(getString(R.string.activity_about_github_url))
        }

        findViewById(R.id.activity_about_button_license).setOnClickListener {

            val builder = AlertDialog.Builder(this)
                    .setTitle(R.string.activity_about_license_dialog_title)
                    .setMessage(R.string.activity_about_apache_license_text)
                    .setPositiveButton("OK", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog?.dismiss()
                        }
                    })
                    .create()

            builder.setCancelable(true)
            builder.setCanceledOnTouchOutside(true)

            builder.show()
        }
    }

    private fun setupVersion() {
        val version = findViewById(R.id.activitiy_about_version) as TextView
        version.setText(String.format(getString(R.string.activity_about_version), BuildConfig.VERSION_NAME))
    }

    private fun setupLibraries() {
        val recyclerView = findViewById(R.id.activitiy_about_recyclerview) as RecyclerView

        val recylerViewLayoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = recylerViewLayoutManager

        val example = RecyclerViewAdapter.LibraryItem("AboutLibraries", "Mike Penz", "5.9.3", "This page is inspired by Mike Penz´s library", "Apache 2.0", "https://github.com/mikepenz/AboutLibraries")
        val example1 = RecyclerViewAdapter.LibraryItem("RevealLayout", "kyze8439690", "1.0.2", "A layout which can make some reveal animation which is introduced in material design guideline.", "Apache 2.0", "https://github.com/kyze8439690/RevealLayout")
        val example2 = RecyclerViewAdapter.LibraryItem("Material Color Picker", "Simone Pessotto", "1.0.4", "A simple, minimalistic and beautiful dialog color picker for Android 4.1+ devices. This color picker is easy-to-use and easy-to-integrate in your application to let users of your app choose color in a simple way.", "MIT   ", "https://github.com/Pes8/android-material-color-picker-dialog")

        val test = ArrayList<RecyclerViewAdapter.LibraryItem>()
        test.add(example)
        test.add(example1)
        test.add(example2)

        val recyclerViewAdapter = RecyclerViewAdapter(this, test, object : RecyclerViewAdapter.OnClickListener {
            override fun onLibraryClick(url: String) {
                openURLInBrowser(url)
            }
        })

        recyclerView.adapter = recyclerViewAdapter
        recyclerView.isNestedScrollingEnabled = false

        val scrollview = findViewById(R.id.scrollview_About) as NestedScrollView
        scrollview.fullScroll(ScrollView.FOCUS_UP)
    }

    private fun openURLInBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(browserIntent);
        } else {
            Toast.makeText(application, "Failed to open website in browser for: $url", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupActionbar() {
        val myToolbar = findViewById(R.id.activitiy_about_toolbar) as Toolbar
        setSupportActionBar(myToolbar)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayShowTitleEnabled(false)

        val upArrow = ContextCompat.getDrawable(application, R.drawable.abc_ic_ab_back_material)
        upArrow.setColorFilter(ContextCompat.getColor(application, R.color.upindicator_color), PorterDuff.Mode.SRC_ATOP)
        actionbar?.setHomeAsUpIndicator(upArrow)
    }

    companion object {

        fun GetStartIntent(context: Context): Intent {
            return Intent(context, AboutActivity::class.java)
        }
    }
}