package com.fourninetyfour.makeplans

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate
import android.support.v7.view.ContextThemeWrapper
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menu.clear()
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        return if (id == R.id.action_settings) {
            val fragment = SettingsActivity()
            fragmentTransaction.add(R.id.MainFragment, fragment as Fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            return true
        } else super.onOptionsItemSelected(item)

    }

    override fun getTheme(): Resources.Theme {
        val theme = super.getTheme()
        val prefs = this.getSharedPreferences("settings", Context.MODE_PRIVATE)
        if (prefs.getBoolean("darkMode", false))
            theme.applyStyle(R.style.darkTheme, true)
        else
            theme.applyStyle(R.style.AppTheme, true)
        return theme
    }
}




