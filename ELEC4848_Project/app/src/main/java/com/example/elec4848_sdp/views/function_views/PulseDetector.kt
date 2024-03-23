package com.example.elec4848_sdp.views.function_views

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.Surface
import android.view.TextureView
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import com.example.elec4848_sdp.functions.pulse_detector.CameraService
import com.example.elec4848_sdp.functions.pulse_detector.OutputAnalyzer
import com.example.elec4848_sdp.R
import com.example.elec4848_sdp.views.main_views.MainMenu
import com.google.android.material.snackbar.Snackbar

class PulseDetector : AppCompatActivity(), OnRequestPermissionsResultCallback {
    private var analyzer: OutputAnalyzer? = null
    private val REQUEST_CODE_CAMERA = 0

    @SuppressLint("HandlerLeak")
    private val mainHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == MESSAGE_UPDATE_REALTIME) {
                (findViewById<View>(R.id.textView) as TextView).text = msg.obj.toString()
            }
            if (msg.what == MESSAGE_UPDATE_FINAL) {
                (findViewById<View>(R.id.editText) as EditText).setText(msg.obj.toString())
            }
            if (msg.what == MESSAGE_CAMERA_NOT_AVAILABLE) {
                Log.println(Log.WARN, "camera", msg.obj.toString())
                (findViewById<View>(R.id.textView) as TextView).setText(
                    R.string.camera_not_found
                )
                analyzer?.stop()
            }
        }
    }
    private val cameraService: CameraService = CameraService(this, mainHandler)

    override fun onPause() {
        super.onPause()
        cameraService.stop()
        if (analyzer != null) analyzer!!.stop()
        analyzer = OutputAnalyzer(this, findViewById(R.id.graphTextureView), mainHandler)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulse)
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.CAMERA),
            REQUEST_CODE_CAMERA
        )

        val toolbar = findViewById<Toolbar>(R.id.HeartBeat_toolbar)
        setSupportActionBar(toolbar)

        val returnButton = toolbar.findViewById(R.id.returnButton) as ImageButton
        returnButton.setOnClickListener {
            changeActivity()
        }

        val textView = toolbar.findViewById(R.id.toolbar_name) as TextView
        textView.text = "Pulse Detector"

        val startMeasurementButton: Button = findViewById(R.id.Measurement)
        startMeasurementButton.setOnClickListener{
            onClickNewMeasurement()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_CAMERA) {
            if (!(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Snackbar.make(
                    findViewById(R.id.constraintLayout),
                    getString(R.string.cameraPermissionRequired),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    fun onClickNewMeasurement() {
        analyzer = OutputAnalyzer(this, findViewById(R.id.graphTextureView), mainHandler)

        // clear prior results
        val empty = CharArray(0)
        (findViewById<View>(R.id.editText) as EditText).setText(empty, 0, 0)
        (findViewById<View>(R.id.textView) as TextView).setText(empty, 0, 0)

        // hide the new measurement item while another one is in progress in order to wait
        // for the previous one to finish
        // Exporting results cannot be done, either, as it would read from the already cleared UI.
        val cameraTextureView = findViewById<TextureView>(R.id.textureView2)
        val previewSurfaceTexture = cameraTextureView.surfaceTexture
        if (previewSurfaceTexture != null) {
            // this first appears when we close the application and switch back
            // - TextureView isn't quite ready at the first onResume.
            val previewSurface = Surface(previewSurfaceTexture)
            cameraService.start(previewSurface)
            analyzer!!.measurePulse(cameraTextureView, cameraService)
        }
    }

    private fun changeActivity() {
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }

    companion object {
        const val MESSAGE_UPDATE_REALTIME = 1
        const val MESSAGE_UPDATE_FINAL = 2
        const val MESSAGE_CAMERA_NOT_AVAILABLE = 3
    }
}