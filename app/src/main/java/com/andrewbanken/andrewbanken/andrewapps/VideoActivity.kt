package com.andrewbanken.andrewbanken.andrewapps

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import kotlinx.android.synthetic.main.layout_3_video.*

class  VideoActivity:  AppCompatActivity(){

    private var playbackPostion = 0
    private var playbackPostion2 = 0

    private val andrewVideo = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov"

    private lateinit var mediaController1: MediaController
    private lateinit var mediaController2: MediaController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_3_video)
        supportActionBar!!.title ="Video demos "
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.show()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "This takes you back to the Home Menu", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            val intent = Intent(this@VideoActivity,MainActivity::class.java)

            startActivity(intent)

            finish()
        }

        mediaController1 = MediaController(this)

        videoView.setOnPreparedListener {
            mediaController1.setAnchorView(videoContainer)
            videoView.setMediaController(mediaController1)
            videoView.seekTo(playbackPostion)
            videoView.start()
        }
        videoView.setOnInfoListener { player, what, extras ->
            if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                progressBar.visibility = View.INVISIBLE
            true
        }

        mediaController2 = MediaController(this)
        val andrewVideo2 = "android.resource://" + packageName + "/raw/" + R.raw.salvideo
        val videoView2 = findViewById<View>(R.id.videoView2) as VideoView
        videoView2.setVideoURI(Uri.parse(andrewVideo2))

        videoView2.setOnPreparedListener {
            mediaController2.setAnchorView(videoContainer2)
            videoView2.setMediaController(mediaController2)
            videoView2.seekTo(playbackPostion)
            videoView2.start()
        }

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
            R.id.action_settings ->{
               return true}
            android.R.id.home -> {
                val intent = Intent(this@VideoActivity,MainActivity::class.java)

                startActivity(intent)

                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }
    override fun onStart() {
        super.onStart()

        val uri = Uri.parse(andrewVideo)
        videoView.setVideoURI(uri)
        progressBar.visibility = View.VISIBLE


    }

    override fun onPause() {
        super.onPause()
        videoView.pause()
        playbackPostion = videoView.currentPosition

        super.onPause()
        videoView2.pause()
        playbackPostion2 = videoView2.currentPosition
    }

    override fun onStop() {
        videoView.stopPlayback()
        super.onStop()
        videoView2.stopPlayback()
        super.onStop()
    }
    override fun onBackPressed() {


        val intent = Intent(this@VideoActivity,MainActivity::class.java)

        startActivity(intent)
        finish()

    }
}