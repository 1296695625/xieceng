package audiovideo

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.io.File

class MyVideoSurfaceView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private lateinit var surfaceHolder: SurfaceHolder
    private lateinit var player: MediaPlayer
    private val tag = "MyVideoSurfaceView"

    constructor(context: Context, attrs: AttributeSet) : this(context) {
        surfaceHolder = holder
        holder.addCallback(this)
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        setZOrderOnTop(true);
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        Log.v(tag, "surface create")
        object :Thread() {
            override fun run() {
                super.run()
                play()
            }
        }.start()
    }

    private fun play() {
//        val input=resources.assets.
//         MediaPlayer: Error (1,-2147483648)  视频文件格式不对
        //放在assets下
        val afd=resources.assets.openFd("1.mp4")

//        E/MediaPlayer: Should have subtitle controller already set 需要设置弹幕
        //放在sdk
        val file = File("/storage/emulated/legacy/Pictures/1.mp4")

        player = MediaPlayer()
        val audioAttributes =
            AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MOVIE).build()
        player.setAudioAttributes(audioAttributes)
//        player.setAudioStreamType(AudioManager.STREAM_MUSIC)//过时
//        player.setDataSource(file.absolutePath)//sd卡路径
        player.setDataSource(afd.fileDescriptor,afd.startOffset,afd.length)//assets下
        player.setDisplay(surfaceHolder)
        player.prepareAsync()
        player.setOnPreparedListener {
            Log.v(tag, "start")
            it.start()
        }
        player.setOnCompletionListener {
            Log.v(tag, "" + it.isPlaying)
            replay()
        }
        player.setOnErrorListener { mp, what, extra ->
            Log.v(tag, "播放失败")
            false
        }
    }

    private fun replay() {
        if (null != player) {
            if (player.isPlaying) player.stop()
            player.release()
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        Log.v(tag, "change")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Log.v(tag, "destroy")
        stopPlay()
    }

    private fun stopPlay() {
        if (null != player) {
//            if (player.isPlaying) player.stop()
            player.release()
        }
    }

}