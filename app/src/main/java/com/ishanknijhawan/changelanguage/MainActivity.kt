package com.ishanknijhawan.changelanguage

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val singleItems = arrayOf("English", "हिन्दी", "français", "español", "日本語")
    private var checkedItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLang.setOnClickListener {
            showLanguageDialog()
        }
    }

    private fun showLanguageDialog() {

        MaterialAlertDialogBuilder(this)
            .setTitle("Select Language")
            .setPositiveButton("Proceed") { dialog, which ->
                when(checkedItem){
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
                val intent = Intent(this, ShowLanguageActivity::class.java)
                intent.putExtra("check", checkedItem)
                startActivity(intent)
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