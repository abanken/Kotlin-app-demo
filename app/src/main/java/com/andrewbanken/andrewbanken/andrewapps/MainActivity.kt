package com.andrewbanken.andrewbanken.andrewapps

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.appcompat.R.id.message
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.andrewbanken.andrewbanken.andrewapps.R.drawable.circle
import com.andrewbanken.andrewbanken.andrewapps.R.drawable.id_home_icon
import com.imangazaliev.circlemenu.CircleMenu
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val circleMenu = findViewById<View>(R.id.circleMenu) as CircleMenu
        circleMenu.setOnItemClickListener { menuButton ->
            when (menuButton.id) {
                R.id.circleMenu->{

                }
                R.id.circle_math -> {
                    Toast.makeText(this, "Math Game", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivity, MathActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.circle_weather -> {
                    Toast.makeText(this, "Weather", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivity, WeatherActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.circle_video -> {
                    Toast.makeText(this, "Video Player", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivity, VideoActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }

            circleMenu.setStateUpdateListener(object : CircleMenu.OnStateUpdateListener {
                override fun onMenuExpanded() {
                    Log.d("CircleMenuStatus", "Expanded")
                }

                override fun onMenuCollapsed() {
                    Log.d("CircleMenuStatus", "Collapsed")
                }
            })

        }

    }
    override fun onBackPressed() {

            finish()

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
