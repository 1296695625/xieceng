package audiovideo

import android.Manifest
import android.bluetooth.BluetoothA2dp
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.WindowManager
import com.example.xieceng.R
import kotlinx.android.synthetic.main.activity_video_surface_view.*
import java.util.*

class VideoSurfaceViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale("android.permission.READ_EXTERNAL_STORAGE")) {

                } else {//去向设置
                    val toSetting=Intent()
                    toSetting.action= Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri= Uri.fromParts("package",packageName,null)
                    startActivity(toSetting)
                }
            }
            requestPermissions(arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE
            ), 100);
        }
        setContentView(R.layout.activity_video_surface_view)

//        videplay.
//        setContentView(MyVideoSurfaceView(this))
        initial()
    }

    private fun initial() {
        val adapter = BluetoothAdapter.getDefaultAdapter()
        adapter.startDiscovery()
        adapter.bondedDevices
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            adapter.listenUsingInsecureL2capChannel()
        }
        var device = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            adapter.listenUsingL2capChannel()
        } else {
        }
        adapter.bluetoothLeScanner
        val scanner = adapter.bluetoothLeScanner
        val socket = adapter.listenUsingRfcommWithServiceRecord("", UUID.randomUUID())
    }

}