package com.example.xieceng

import android.content.Intent
import android.net.Network
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import audiovideo.SurfaceViewActivity
import audiovideo.VideoSurfaceViewActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var viewModel: MainViewModel;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv: TextView = findViewById(R.id.tv);
        surfaceview.setOnClickListener(this)
        bt_getstr.setOnClickListener(this)
        videosurface.setOnClickListener(this)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.tv.observe(this) { value ->
            value.let {
                Log.v("tag", "it--" + it)
                tv.text = it
            }
        }

    }


    override fun onClick(v: View) {
        if (v.id == R.id.bt_getstr) viewModel.MainViewModelClick()
        if (v.id == R.id.surfaceview) startActivity(Intent(this, SurfaceViewActivity::class.java))
        if (v.id == R.id.videosurface) startActivity(
            Intent(
                this,
                VideoSurfaceViewActivity::class.java
            )
        )
    }
}