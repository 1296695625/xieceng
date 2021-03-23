package audiovideo

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xieceng.R
import kotlinx.android.synthetic.main.activity_video_surface_view.*

class VideoSurfaceViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            requestPermissions(arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),100);
        }
        setContentView(R.layout.activity_video_surface_view)
//        videplay.
//        setContentView(MyVideoSurfaceView(this))
    }
}