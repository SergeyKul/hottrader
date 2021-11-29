package com.sirko.hottrader

import android.app.AlertDialog
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {
    private val model: HottraderViewModel
        get() = ViewModelProvider(this)[HottraderViewModel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Hottrader)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.addOnBackStackChangedListener(this)
        model.message.observe(this, { message ->
            if (message != "") showMessage(message)
        })
        onBackStackChanged()
    }

    private fun showMessage(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Error!")
            .setMessage(message)
            .setPositiveButton("Ok") { dialog, _ -> dialog.cancel() }
            .create()
            .show()
        model.message.value = ""
    }

    override fun onBackStackChanged() {
        supportFragmentManager.backStackEntryCount.let {
            if (it == 0) model.current.value = -1
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                val catalog: View = findViewById(R.id.fragment_catalog)
                catalog.visibility = if (it == 0) View.VISIBLE else View.GONE
                title = if (it == 0) getString(R.string.app_name) else ""
                supportActionBar?.setDisplayHomeAsUpEnabled(it != 0)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) supportFragmentManager.popBackStack()
        return super.onOptionsItemSelected(item)
    }
}