package com.ishanknijhawan.changelanguage

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_show_language.*
import java.util.*

class ShowLanguageActivity : AppCompatActivity() {

    private val singleItems = arrayOf("English", "हिन्दी", "français", "español", "日本語")
    private var checkedItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_language)

        checkedItem = intent.getIntExtra("check", 0)
        Log.d("check", "onCreate: value is $checkedItem")

        tvShowLanguage.text = resources.getString(R.string.Welcome)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings) {
            openLanguageChooser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openLanguageChooser() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Select Language")
            .setPositiveButton("Proceed") { dialog, which ->
                when (checkedItem) {
                    0 -> {
                        setLocale("en")
                    }
                    1 -> {
                        setLocale("hi")
                    }
                    2 -> {
                        setLocale("fr")
                    }
                    3 -> {
                        setLocale("es")
                    }
                    4 -> {
                        setLocale("ja")
                    }
                }
                tvShowLanguage.text = resources.getString(R.string.Welcome)

            }
            // Single-choice items (initialized with checked item)
            .setSingleChoiceItems(singleItems, checkedItem) { dialog, which ->
                checkedItem = which
            }
            .show()
    }

    private fun setLocale(s: String) {
        val locale = Locale(s)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
    }
}