package audiovideo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.xieceng.R

class MySurfaceView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private lateinit var surfaceHolder: SurfaceHolder;
    private lateinit var paint: Paint;
    private lateinit var bitmap: Bitmap
    constructor(context: Context,attrs: AttributeSet)  :this(context){
        surfaceHolder=holder//得到控制器
        surfaceHolder.addCallback(this)//获得回调
        paint = Paint()
        bitmap=BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
    }
    init {

    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        val canvas=holder.lockCanvas()
        canvas.drawBitmap(bitmap, Matrix(),paint)
        surfaceHolder.unlockCanvasAndPost(canvas)
    }


    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        //变化时的回调
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        //销毁时的回调
    }


}